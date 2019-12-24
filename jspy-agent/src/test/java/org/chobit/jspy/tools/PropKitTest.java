package org.chobit.jspy.tools;

import org.junit.Assert;
import org.junit.Test;

public class PropKitTest {

    static {
        PropKit.load("/jspy.properties");
    }


    @Test
    public void getStr() {
        String s = PropKit.getProp("a");
        Assert.assertEquals("chobit", s);
    }


    @Test
    public void getInt() {
        int i = PropKit.getInt("b");
        Assert.assertEquals(12, i);
    }


}
