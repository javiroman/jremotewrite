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
TIMESERIE: 
   labels {
     name: "__name__"
     value: "scrape_samples_post_metric_relabeling"
   }
   labels {
     name: "instance"
     value: "prometheus:9090"
   }
   labels {
     name: "job"
     value: "prometheus"
   }
   samples {
     value: 445.0
     timestamp: 1603104079293
   }
   
   TIMESERIE: 
   labels {
     name: "__name__"
     value: "scrape_series_added"
   }
   labels {
     name: "instance"
     value: "prometheus:9090"
   }
   labels {
     name: "job"
     value: "prometheus"
   }
   samples {
     timestamp: 1603104079293
   }
[..]

```
