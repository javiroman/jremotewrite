package test.java.jremotewrite;

import main.java.jremotewrite.PrometheusHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.junit.After;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import prometheus.Remote.WriteRequest;

public class TestPrometheusHandler {
    private final int PORT = 8080;
    private final int MAX_BATCH = 0;
    private final String CONTEXT_PATH = "/test";
    private Server server;

    @Before
    public void InitServer() throws Exception {
        Server server = new Server(PORT);

        ContextHandler context = new ContextHandler();
        context.setContextPath(CONTEXT_PATH);
        context.setHandler(new PrometheusHandler(MAX_BATCH));

        server.setHandler(context);
        server.start();
    }

    @Test
    public void test() {
        /*
          We have to create Prometheus PB message
          and send compressed with snappy to the
          test server with the real handler.
         */
        WriteRequest.Builder builder = WriteRequest.newBuilder();

        //builder.setTimeseries();

        WriteRequest message = builder.build();

        //Assert.fail("Not yet implemented");
        Assume.assumeTrue(true);
    }

    @After
    public void ShutdownServer() throws Exception {
        server.stop();
        server.destroy();
    }
}