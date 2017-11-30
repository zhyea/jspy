package com.zhyea.jspy.agent.tools;

import junit.framework.TestCase;

public class ObjectMeasureTest extends TestCase {


    public void testMeasure() throws IllegalAccessException {

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(ObjectMeasure.shallowSize(arr));
        System.out.println(ObjectMeasure.retainedSize(arr));

    }


    public void testGetObjectSize() {

    }

}
