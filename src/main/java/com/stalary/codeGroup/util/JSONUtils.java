package com.stalary.codeGroup.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * @author Peter on 2017-03-02.
 */
public class JSONUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();


    public static String toJsonString(Object object) {
        String result = null;
        try {
            result = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static Map<String, Object> toMap(String jsonString) {
        TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>() {
        };

        Map<String, Object> result = null;
        try {
            result = objectMapper.readValue(jsonString, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }
}
