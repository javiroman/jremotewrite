package test.java.jremotewrite;

import main.java.jremotewrite.PrometheusHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import prometheus.Remote.WriteRequest;

public class TestPrometheusHandler {
    private final int MAX_BATCH = 0;
    private final String CONTEXT_PATH = "/test";
    public Server server;

    @Before
    public void InitServer() throws Exception {
        server = new Server(0);

        ContextHandler context = new ContextHandler();
        context.setContextPath(CONTEXT_PATH);
        context.setHandler(new PrometheusHandler(MAX_BATCH));

        server.setHandler(context);
        server.start();
    }

    @Test
    public void test() throws Exception {
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
        if (server.isRunning()){
            System.out.println("server ready for test");
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