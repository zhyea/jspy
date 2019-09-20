package org.chobit.jspy;

import java.lang.instrument.Instrumentation;


public class JSpyAgent {


    private static Instrumentation instrumentation;

    public static void premain(String options, Instrumentation ins) {

        ins.addTransformer(new JSpyTransformer());

        if (null != instrumentation) {
            throw new AssertionError("instrumentation already initialized.");
        }

        instrumentation = ins;
    }


}
