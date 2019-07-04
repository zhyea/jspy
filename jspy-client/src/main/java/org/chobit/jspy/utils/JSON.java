package org.chobit.jspy.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class JSON {


    private static Logger logger = LoggerFactory.getLogger(JSON.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();


    public static <T> String toJson(T src) {
        try {
            return MAPPER.writeValueAsString(src);
        } catch (Exception e) {
            logger.error("Parsing src object to json string failed.", e);
            return null;
        }
    }


}
