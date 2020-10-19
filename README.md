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
{"label":[{"name":"le","value":"+Inf"},{"name":"le","value":"+Inf"},{"name":"le","value":"+Inf"},{"name":"le","value":"+Inf"}],"sample":"1188.0","timestamp":"1603128319294"}
{"label":[{"name":"le","value":"153.7734375"},{"name":"le","value":"153.7734375"},{"name":"le","value":"153.7734375"},{"name":"le","value":"153.7734375"}],"sample":"1188.0","timestamp":"1603128319294"}
{"label":[{"name":"le","value":"364.5"},{"name":"le","value":"364.5"},{"name":"le","value":"364.5"},{"name":"le","value":"364.5"}],"sample":"1093.0","timestamp":"1603128319294"}
{"label":[{"name":"le","value":"8"},{"name":"le","value":"8"},{"name":"le","value":"8"},{"name":"le","value":"8"}],"sample":"1.0","timestamp":"1603128319294"}
{"label":[{"name":"job","value":"prometheus"},{"name":"job","value":"prometheus"},{"name":"job","value":"prometheus"}],"sample":"0.0","timestamp":"1603128319294"}
{"label":[{"name":"job","value":"prometheus"},{"name":"job","value":"prometheus"},{"name":"job","value":"prometheus"}],"sample":"1.6031232E12","timestamp":"1603128319294"}
{"label":[{"name":"job","value":"prometheus"},{"name":"job","value":"prometheus"},{"name":"job","value":"prometheus"}],"sample":"1.603008363E9","timestamp":"1603128319294"}
{"label":[{"name":"le","value":"0.01"},{"name":"le","value":"0.01"},{"name":"le","value":"0.01"},{"name":"le","value":"0.01"}],"sample":"0.0","timestamp":"1603128319294"}
{"label":[{"name":"job","value":"prometheus"},{"name":"job","value":"prometheus"},{"name":"job","value":"prometheus"}],"sample":"0.0","timestamp":"1603128319294"}
{"label":[{"name":"job","value":"prometheus"},{"name":"job","value":"prometheus"},{"name":"job","value":"prometheus"}],"sample":"1.0","timestamp":"1603128319294"}
{"label":[{"name":"job","value":"prometheus"},{"name":"job","value":"prometheus"},{"name":"job","value":"prometheus"},{"name":"job","value":"prometheus"}],"sample":"397.0","timestamp":"1603128319294"}
```
