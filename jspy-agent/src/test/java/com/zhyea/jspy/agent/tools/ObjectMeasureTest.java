package com.zhyea.jspy.agent.tools;

import com.zhyea.jspy.agent.JSpyAgent;
import com.zhyea.jspy.agent.tools.common.Basket;
import junit.framework.TestCase;

import java.lang.instrument.Instrumentation;

public class ObjectMeasureTest extends TestCase {


    public void testMeasure() throws IllegalAccessException {
        System.out.println(ObjectMeasure.measure(new Basket()));
    }


    public void testGetObjectSize() {
        Instrumentation ins = JSpyAgent.getInstrumentation();
        long sizeInteger = ins.getObjectSize(new Integer(1));
        System.out.println(sizeInteger);
        long sizeObject = ins.getObjectSize(new Basket());
        System.out.println(sizeObject);
    }

}
