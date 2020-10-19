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

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.logging.Logger;

import com.google.protobuf.util.JsonFormat;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.xerial.snappy.Snappy;

import org.xerial.snappy.SnappyInputStream;
import prometheus.Remote.WriteRequest;
import prometheus.Types;

public class PrometheusHandler extends AbstractHandler {

    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private static JsonFormat.Printer JSON_PRINTER = JsonFormat.printer();

    public PrometheusHandler() {
        super();
        logger.info("constructor handler");
    }

    @Override
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException,ServletException {

        /*
            Retrieves the body of the request as binary data:
            The data is protobuf compressed with snappy.
         */
        try (SnappyInputStream is = new SnappyInputStream(baseRequest.getInputStream())) {

            if (is == null) {
                logger.warning("inputstream is null");
            }

            WriteRequest writeRequest = WriteRequest.parseFrom(is);

            ServletOutputStream out = response.getOutputStream();
            out.flush();

            for (Types.TimeSeries s: writeRequest.getTimeseriesList()) {
                System.out.println("TIMESERIE: ");
                System.out.println(s);
            }

            //String json = JSON_PRINTER.print(writeRequest);
            //System.out.println(json);
            //System.out.println(writeRequest);
        }
        catch (IOException e) {
            throw e;
        }
    }
}