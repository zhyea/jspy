package com.zhyea.jspy.core.constants;

import junit.framework.TestCase;

import static com.zhyea.jspy.core.constants.GarbageCollector.PSMarkSweep;

public class GarbageCollectorTest extends TestCase {

    public void testGetByName() {
        String name = "PS MarkSweep";
        GarbageCollector gc = GarbageCollector.nameOf(name);
        assertEquals(PSMarkSweep, gc);
    }

}
