package org.chobit.jspy;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.chobit.jspy.service.beans.Memory;

import java.lang.reflect.Field;

public class Test {


    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        Field[] fields = Memory.class.getFields();
        for(Field f : fields){
            System.out.println(f.getName());
        }
        System.out.println("------------------");
        Field[] fields2 = Memory.class.getDeclaredFields();
        for(Field f : fields2){
            System.out.println(f.getName());
        }
    }
}
