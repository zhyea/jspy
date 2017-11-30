package com.zhyea.jspy.sample.asm;


import com.zhyea.jspy.sample.model.TestModel;

public class TracerClient {

    public static void main(String[] args) {
        ClassTracer.trace(TestModel.class);
    }

}
