package com.zhyea.jspy.agent.tools;

import com.zhyea.jspy.agent.JSpyAgent;
import com.zhyea.jspy.agent.tools.common.Basket;
import junit.framework.TestCase;

import java.lang.instrument.Instrumentation;

public class ObjectMeasureTest extends TestCase {


    public void testMeasure() throws IllegalAccessException {
        /*String[] arr = {"1", "5", "6"};
        String[] arr2 = {"2", "3", "4", "5", "6"};
        System.out.println(ObjectMeasure.shallowSize(arr));
        System.out.println(ObjectMeasure.shallowSize(arr2));*/
        //System.out.println(ObjectMeasure.retainedSize(new Integer(16)));


        System.out.println(ObjectMeasure.shallowSize(null));

        System.out.println(ObjectMeasure.retainedSize("1234"));
        System.out.println(ObjectMeasure.retainedSize("12341234"));
        System.out.println(ObjectMeasure.retainedSize("123412341234"));
        System.out.println(ObjectMeasure.retainedSize("1234123412341234"));
        System.out.println(ObjectMeasure.retainedSize("12341234123412341234"));
        System.out.println(ObjectMeasure.retainedSize("123412341234123412341234"));
        System.out.println(ObjectMeasure.retainedSize("1234123412341234123412341234"));
    }


    public void testGetObjectSize() {
        Instrumentation ins = JSpyAgent.getInstrumentation();
        long sizeInteger = ins.getObjectSize(new Integer(1));
        System.out.println(sizeInteger);
        long sizeObject = ins.getObjectSize(new Basket());
        System.out.println(sizeObject);
    }

}
