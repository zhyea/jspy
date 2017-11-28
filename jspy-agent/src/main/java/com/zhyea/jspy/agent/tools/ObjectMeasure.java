package com.zhyea.jspy.agent.tools;

import com.zhyea.jspy.agent.JSpyAgent;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ObjectMeasure {

    private static Instrumentation ins = JSpyAgent.getInstrumentation();


    private List<Object> getAllFields(Object obj) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        List<Object> all = new ArrayList<>();
        all.add(obj);

        for (Field f : fields) {
            f.setAccessible(true);
        }

        return null;
    }

}
