package com.koderme.java.sre.SreCap.controller;

import org.springframework.web.client.RestTemplate;

public class GetRestResponse {

    public static String invoke(String uri) {

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class);
    }
}

