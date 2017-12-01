package com.zhyea.jspy.sample;

import com.zhyea.jspy.annotations.JSpyTimer;
import com.zhyea.jspy.sample.model.TestModel;

public class TestClient {


    public static void main(String[] args) {

        Class clazz = TestModel.class;
        System.out.println(clazz.getAnnotation(JSpyTimer.class));
    }

}
