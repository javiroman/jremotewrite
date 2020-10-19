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

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;

public class JRemoteWrite {

    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private static Server createServer(final int port){
        Server server = new Server(port);

        ContextHandler context = new ContextHandler();
        context.setContextPath("/v1/write");
        context.setHandler(new PrometheusHandler());

        server.setHandler(context);
        return server;
    }

    public static void main(String[] args) throws Exception {
        logger.info("Starting Prometheus Remote Write Endpoint: ");
        Server server = createServer(8000);
        server.start();
        server.join();
    }
}
