package test.java.jremotewrite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import prometheus.Remote.WriteRequest;

public class TestPrometheusHandler {
    @Before
    public void InitServer() {

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

        Assert.fail("Not yet implemented");
    }

    @After
    public void ShutdownServer() {

    }
}