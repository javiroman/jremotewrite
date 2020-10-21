# jremotewrite

Java Prometheus Remote Write Endpoint

# Usage

```
$ docker-compose up
Starting nodeexporter ... done
Starting prometheus   ... done
Attaching to prometheus, nodeexporter
[...]

$ mvn package
$ java -jar target/jremotewrite-1.0-SNAPSHOT-uber.jar 
Oct 19, 2020 12:44:07 PM main.java.jremotewrite.JRemoteWrite main
INFO: Starting Prometheus Remote Write Endpoint: 
2020-10-19 12:44:07.548:INFO::main: Logging initialized @271ms
[..]
```

# Outptut

```
{"metricLabels":[{"name":"__name__","value":"prometheus_web_federation_errors_total"},{"name":"instance","value":"prometheus:9090"},{"name":"job","value":"prometheus"}],"metricSamples":[{"sample":"0.0","timestamp":"1603283469293"}]}                                        
{"metricLabels":[{"name":"__name__","value":"prometheus_web_federation_warnings_total"},{"name":"instance","value":"prometheus:9090"},{"name":"job","value":"prometheus"}],"metricSamples":[{"sample":"0.0","timestamp":"1603283469293"}]}                                      
{"metricLabels":[{"name":"__name__","value":"promhttp_metric_handler_requests_in_flight"},{"name":"instance","value":"prometheus:9090"},{"name":"job","value":"prometheus"}],"metricSamples":[{"sample":"1.0","timestamp":"1603283469293"}]}                                    
{"metricLabels":[{"name":"__name__","value":"promhttp_metric_handler_requests_total"},{"name":"code","value":"200"},{"name":"instance","value":"prometheus:9090"},{"name":"job","value":"prometheus"}],"metricSamples":[{"sample":"241.0","timestamp":"1603283469293"}]}        
{"metricLabels":[{"name":"__name__","value":"promhttp_metric_handler_requests_total"},{"name":"code","value":"500"},{"name":"instance","value":"prometheus:9090"},{"name":"job","value":"prometheus"}],"metricSamples":[{"sample":"0.0","timestamp":"1603283469293"}]}          
{"metricLabels":[{"name":"__name__","value":"promhttp_metric_handler_requests_total"},{"name":"code","value":"503"},{"name":"instance","value":"prometheus:9090"},{"name":"job","value":"prometheus"}],"metricSamples":[{"sample":"0.0","timestamp":"1603283469293"}]}          
{"metricLabels":[{"name":"__name__","value":"up"},{"name":"instance","value":"prometheus:9090"},{"name":"job","value":"prometheus"}],"metricSamples":[{"sample":"1.0","timestamp":"1603283469293"}]}                                                                            
{"metricLabels":[{"name":"__name__","value":"scrape_duration_seconds"},{"name":"instance","value":"prometheus:9090"},{"name":"job","value":"prometheus"}],"metricSamples":[{"sample":"0.015079253","timestamp":"1603283469293"}]}                                               
{"metricLabels":[{"name":"__name__","value":"scrape_samples_scraped"},{"name":"instance","value":"prometheus:9090"},{"name":"job","value":"prometheus"}],"metricSamples":[{"sample":"541.0","timestamp":"1603283469293"}]}                                                      
{"metricLabels":[{"name":"__name__","value":"scrape_samples_post_metric_relabeling"},{"name":"instance","value":"prometheus:9090"},{"name":"job","value":"prometheus"}],"metricSamples":[{"sample":"541.0","timestamp":"1603283469293"}]}                                       
{"metricLabels":[{"name":"__name__","value":"scrape_series_added"},{"name":"instance","value":"prometheus:9090"},{"name":"job","value":"prometheus"}],"metricSamples":[{"sample":"0.0","timestamp":"1603283469293"}]} 
```
