package com.koderme.java.sre.SreCap.controller;


import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;

@RestController
public class MainController {


    // Single item
    @GetMapping("/metrics")
    String getAllMetrics() {

        String uri = "http://localhost:9090/api/v1/label/__name__/values";
        String result = GetRestResponse.invoke(uri);
        String resultNewline = result.replaceAll(",", "\n");
        return resultNewline;
    }

    // Get all label name
    @GetMapping("/metric/labels")
    String getLabels() {

        String uri = MessageFormat.format("http://localhost:9090/api/v1/labels", "");
        String result = GetRestResponse.invoke(uri);
        String resultNewline = result.replaceAll(",", "\n");
        return resultNewline;
    }

    // Get specific label values
    @GetMapping("/metric/label/{labelName}")
    String getLabelValues(@PathVariable String labelName) {

        String uri = MessageFormat.format("http://localhost:9090/api/v1/label/{0}/values", labelName);
        System.out.printf("uri=" + uri);
        String result = GetRestResponse.invoke(uri);
        String resultNewline = result.replaceAll(",", "\n");
        return resultNewline;
    }

    @GetMapping("/metric/instant")
    @ResponseBody
    String getInstantMetric(@RequestParam(required = true) String query, @RequestParam(required = false) String time) {

        String uri = MessageFormat.format("http://localhost:9090/api/v1/query?query={0}", query);
        if (time != null) {
            uri += MessageFormat.format("&time={0}", time);
        }

        System.out.printf("uri=" + uri);
        return GetRestResponse.invoke(uri);
    }

    @GetMapping("/metric/range")
    @ResponseBody
    String getRangeMetric(@RequestParam(required = true) String query,
                          @RequestParam(required = true) String start,
                          @RequestParam(required = true) String end,
                          @RequestParam(required = true) String step
    ) {

        String uri = MessageFormat.format("http://localhost:9090/api/v1/query_range?query={0}&start={1}&end={2}&step={3}",
                query, start, end, step);

        System.out.printf("[ " + uri + " ]" );

        String result = GetRestResponse.invoke(uri);
        return MiscUtils.parseJson(result).toJSONString();
    }

    @GetMapping("/metric/metadata")
    @ResponseBody
    String getMetricMetadata(@RequestParam(required = true) String metric) {

        String uri = MessageFormat.format("http://localhost:9090/api/v1/metadata?metric={0}", metric);
        System.out.printf("[ " + uri + " ]" );
        return GetRestResponse.invoke(uri);
    }

    @GetMapping("/metrics/metadata")
    @ResponseBody
    String getAllMetricMetadata() {

        String uri = MessageFormat.format("http://localhost:9090/api/v1/metadata{0}", "");
        System.out.printf("[ " + uri + " ]" );
        return GetRestResponse.invoke(uri);
    }

    @GetMapping("/metric/series")
    @ResponseBody
    String getSeriesByLabel(@RequestParam(required = true) String label) {

        String uri = MessageFormat.format("http://localhost:9090/api/v1/series?match[]={0}", label);
        System.out.printf("[ " + uri + " ]" );
        return GetRestResponse.invoke(uri);
    }
}