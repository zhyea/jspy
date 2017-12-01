package com.zhyea.jspy.sample.annotation;

import com.zhyea.jspy.annotations.JSpyTimer;
import com.zhyea.jspy.sample.model.TestModel;

import java.lang.reflect.Method;

public class AnnotationChecker {

    public static void main(String[] args) {
        Class clazz = TestModel.class;
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            JSpyTimer timer = m.getAnnotation(JSpyTimer.class);
            if (null != timer) {
                System.out.println("method: " + m);
            }
        }
    }

}
