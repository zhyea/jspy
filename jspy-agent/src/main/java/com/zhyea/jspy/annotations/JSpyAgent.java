package com.zhyea.jspy.annotations;

import com.zhyea.jspy.annotations.transformer.PrintTransformer;

import java.lang.instrument.Instrumentation;

public class JSpyAgent {


    public static void premain(String options, Instrumentation ins) {

        System.out.println("-----options: " + options);
        ins.addTransformer(new PrintTransformer());

    }


}
