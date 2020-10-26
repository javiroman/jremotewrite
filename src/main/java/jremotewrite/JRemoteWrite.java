/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package main.java.jremotewrite;

import java.util.logging.Logger;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;

public class JRemoteWrite {

    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private static Server createServer(final int port, final String contextPath, final int maxBatch){
        Server server = new Server(port);

        ContextHandler context = new ContextHandler();
        context.setContextPath(contextPath);
        context.setHandler(new PrometheusHandler(maxBatch));

        server.setHandler(context);
        return server;
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 3 || args.length > 4) {
            printUsage();
            return;
        }

        logger.info("Starting Prometheus Remote Write Endpoint: ");
        logger.info("Context Path: " + args[0] + " Port: " + args[1]);
        Server server = createServer(Integer.parseInt(args[1]), args[0], Integer.parseInt(args[2]));
        server.start();
        server.join();
    }

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("java -jar target/jremotewrite-1.0-SNAPSHOT-uber.jar endpoint port max-batch-size");
        System.out.println();
        System.out.println("Example:");
        System.out.println("java -jar target/jremotewrite-1.0-SNAPSHOT-uber.jar /v1/write 8000 500");
        System.out.println();
        System.out.println("Parameters:");
        System.out.println();
        System.out.println("endpoint      : Context path matching the Prometheus remote_write url path");
        System.out.println("port          : Port for listen incoming metrics, matching the Prometheus remote_write url");
        System.out.println("max-batch-size: Print batch of metrics. With 1 no batching at all");
        System.out.println();
    }
}
