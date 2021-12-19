package com.koderme.java.sre.SreCap.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MiscUtils {

    public static JSONObject parseJson(String rawJson) {

        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(rawJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  json;
    }
}
