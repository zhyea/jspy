package org.chobit.jspy.sample.asm;


import org.chobit.jspy.sample.model.TestModel;

public class TracerClient {

    public static void main(String[] args) {
        ClassTracer.trace(TestModel.class);
    }

}
