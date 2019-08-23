package org.chobit.jspy.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public abstract class JSON {


    private static Logger logger = LoggerFactory.getLogger(JSON.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();


    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);

        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);

        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }


    public static <T> String toJson(T src) {
        try {
            return MAPPER.writeValueAsString(src);
        } catch (Exception e) {
            logger.error("Parsing src object to json string failed.", e);
            return null;
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            logger.error("Read from json error, json: {}", json, e);
        }
        return null;
    }


    public static <T> T fromJson(String json, TypeReference<T> tr) {
        try {
            return MAPPER.readValue(json, tr);
        } catch (IOException e) {
            logger.error("Read from json error, json: {}", json, e);
        }
        return null;
    }


    private JSON() {
    }
}
