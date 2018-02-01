package org.chobit.jspy.sample;

import org.chobit.jspy.annotations.JSpyTimer;
import org.chobit.jspy.sample.model.TestModel;

public class TestClient {


    public static void main(String[] args) {

        Class clazz = TestModel.class;
        System.out.println(clazz.getAnnotation(JSpyTimer.class));
    }

}
