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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import org.xerial.snappy.SnappyInputStream;
import prometheus.Remote.WriteRequest;
import prometheus.Types;

public class PrometheusHandler extends AbstractHandler {

    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private static JsonFormat.Printer JSON_PRINTER = JsonFormat.printer();

    private final int maxBatch;
    private int bufferElements = 0;
    List<String> bufferBatch = new ArrayList<>();

    public PrometheusHandler(int maxBatch) {
        super();
        this.maxBatch = maxBatch;
        logger.info("Hanlder Running");
    }

    @Override
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException,ServletException {

        // Retrieves the body of the request as binary data: protobuf compressed with snappy.
        try (SnappyInputStream is = new SnappyInputStream(baseRequest.getInputStream())) {
            if (is == null) {
                logger.warning("inputstream is null");
            }

            ServletOutputStream out = response.getOutputStream();
            response.setStatus(HttpServletResponse.SC_OK);

            WriteRequest writeRequest = WriteRequest.parseFrom(is);
            System.out.println(writeRequest);

            for (Types.TimeSeries timeSeries: writeRequest.getTimeseriesList()) {
                List<MetricLabel> labelsList = new ArrayList<>();
                List<MetricSample> sampleList = new ArrayList<>();
                Metrics metrics = new Metrics();
                Gson gson = new Gson();
                for (Types.Label labelItem: timeSeries.getLabelsList()) {
                    MetricLabel metricsLabel = new MetricLabel();
                    metricsLabel.name = labelItem.getName();
                    metricsLabel.value = labelItem.getValue();
                    labelsList.add(metricsLabel);
                }
                for (Types.Sample sample: timeSeries.getSamplesList()) {
                    MetricSample metricSample = new MetricSample();
                    metricSample.sample = Double.toString(sample.getValue());
                    metricSample.timestamp = Long.toString(sample.getTimestamp());
                    sampleList.add(metricSample);
                }
                metrics.metricLabels= labelsList;
                metrics.metricSamples = sampleList;

                if (maxBatch == 0 || maxBatch == 1) {
                    System.out.println(gson.toJson(metrics));
                } else if (bufferElements < maxBatch) {
                    bufferBatch.add(gson.toJson(metrics));
                    bufferElements++;
                } else {
                    System.out.println("Batch mode bufferBatch size: " + bufferBatch.size());
                    for (String item: bufferBatch) {
                        System.out.println(item);
                    }
                }
            }

            out.flush();
        } catch (InvalidProtocolBufferException e) {
            /*
              While parsing a protocol message, the input ended unexpectedly
              in the middle of a field.  This could mean either than the
              input has been truncated or that an embedded message misreported
              its own length.
             */
            logger.severe(e.toString());
        } catch (IOException e){
            throw e;
        }
    }
}

class Metrics {
    public List<MetricLabel> metricLabels;
    public List<MetricSample> metricSamples;
}

class MetricLabel {
   public String name;
   public String value;
}

class MetricSample {
    public String sample;
    public String timestamp;
}
