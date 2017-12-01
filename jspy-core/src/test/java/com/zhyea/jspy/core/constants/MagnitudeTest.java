package com.zhyea.jspy.core.constants;

import junit.framework.TestCase;

import static com.zhyea.jspy.core.constants.Magnitude.*;

public class MagnitudeTest extends TestCase {

    public void testCompute() {
        long size = 1024 * 1024 * 1024;
        assertEquals(1024 * 1024, KB.compute(size));
        assertEquals(1024, MB.compute(size));
        assertEquals(1, GB.compute(size));
    }

}
