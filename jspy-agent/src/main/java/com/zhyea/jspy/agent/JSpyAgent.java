package com.zhyea.jspy.agent;

import com.zhyea.jspy.agent.transformer.PrintTransformer;

import java.lang.instrument.Instrumentation;

public class JSpyAgent {


    public static void premain(String options, Instrumentation ins) {

        System.out.println("-----options: " + options);
        ins.addTransformer(new PrintTransformer());
    }


}
