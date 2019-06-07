package org.chobit.jspy.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class JSON {


    private static final ObjectMapper MAPPER = new ObjectMapper();


    public static <T> String toJson(T src) throws Exception {
        try {
            return MAPPER.writeValueAsString(src);
        } catch (Exception e) {
            throw e;
        }
    }


}
