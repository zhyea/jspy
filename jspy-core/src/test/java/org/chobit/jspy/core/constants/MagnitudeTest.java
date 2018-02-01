package org.chobit.jspy.core.constants;

import junit.framework.TestCase;

public class MagnitudeTest extends TestCase {

    public void testCompute() {
        long size = 1024 * 1024 * 1024;
        assertEquals(1024 * 1024, Magnitude.KB.compute(size));
        assertEquals(1024, Magnitude.MB.compute(size));
        assertEquals(1, Magnitude.GB.compute(size));
    }

}
