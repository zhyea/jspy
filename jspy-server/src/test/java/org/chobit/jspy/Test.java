package org.chobit.jspy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.LinkedCaseInsensitiveMap;

public class Test {


    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws JsonProcessingException {
        LinkedCaseInsensitiveMap map = new LinkedCaseInsensitiveMap();
        map.put("AAA", 1);
        System.out.println(map.get("aaa"));
        System.out.println(map);
    }
}
