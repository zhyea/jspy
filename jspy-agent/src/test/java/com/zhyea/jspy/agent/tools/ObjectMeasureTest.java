package com.zhyea.jspy.agent.tools;

import com.zhyea.jspy.agent.tools.common.Basket;
import junit.framework.TestCase;

public class ObjectMeasureTest extends TestCase {


    public void testMeasure() throws IllegalAccessException {
        ObjectMeasure.measure(new Basket());
    }

}
