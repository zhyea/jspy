package org.chobit.jspy.agent;

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

    /**
     * 获取instrumentation实例，以便计算对象size
     */
    public static Instrumentation getInstrumentation() {
        if (null == instrumentation) {
            throw new IllegalStateException("Instrumentation is not setup properly.");
        }
        return instrumentation;
    }


}
