package test.java.jremotewrite;

import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Message;
import main.java.jremotewrite.PrometheusHandler;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.InputStreamContentProvider;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.junit.After;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.xerial.snappy.Snappy;
import prometheus.Remote.WriteRequest;
import prometheus.Types;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.util.Arrays;

public class TestPrometheusHandler {
    private final int MAX_BATCH = 0;
    private final String CONTEXT_PATH = "/test";
    public Server server;

    @Before
    public void InitServer() throws Exception {
        server = new Server(0);

        ContextHandler context = new ContextHandler();
        context.setContextPath(CONTEXT_PATH);
        context.setAllowNullPathInfo(true);
        context.setHandler(new PrometheusHandler(MAX_BATCH));

        server.setHandler(context);
        server.start();
    }

    @Test
    public void test() throws Exception {
        /*
          We have to create Prometheus PB message
          and send compressed with snappy to the
          test server with the real handler:

          List TimeSeries
            List Label
                name
                value
            List Sample:
                value
                timestamp
         */
        WriteRequest.Builder writeRequestBuilder = WriteRequest.newBuilder();

        Types.TimeSeries.Builder timeSeriesBuilder = Types.TimeSeries.newBuilder();
        Types.Label.Builder labelBuilder = Types.Label.newBuilder();
        Types.Sample.Builder sampleBuilder = Types.Sample.newBuilder();


        labelBuilder.setName("name1")
            .setValue("value1");

        labelBuilder.setName("name2")
                .setValue("value2");

        Types.Label l = labelBuilder.build();

        sampleBuilder.setValue(1)
                .setTimestamp(1111111111L);

        Types.Sample s = sampleBuilder.build();

        timeSeriesBuilder.addAllLabels(Arrays.asList(l));
        timeSeriesBuilder.addAllSamples(Arrays.asList(s));

        Types.TimeSeries t = timeSeriesBuilder.build();
        writeRequestBuilder.addAllTimeseries(Arrays.asList(t));

        WriteRequest message = writeRequestBuilder.build();

        byte[] compressedMessage = Snappy.compress(message.toByteArray());

        if (server.isRunning()){
            HttpClient httpClient = new HttpClient();
            httpClient.start();

            ContentResponse response =
                    httpClient.newRequest(server.getURI().toString())
                    .method(HttpMethod.POST)
                    .content(new InputStreamContentProvider(
                            new ByteArrayInputStream(compressedMessage)))
                    .send();

            System.out.println(response.getContentAsString());
            Assert.assertEquals(response.getStatus(), HttpServletResponse.SC_OK);

            httpClient.stop();
        }
    }

    @After
    public void ShutdownServer() {
        try {
            if (server != null){
                System.out.println("Closing Test Server");
                server.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}