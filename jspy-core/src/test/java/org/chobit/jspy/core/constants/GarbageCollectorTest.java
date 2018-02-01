package org.chobit.jspy.core.constants;

import junit.framework.TestCase;

public class GarbageCollectorTest extends TestCase {

    public void testGetByName() {
        String name = "PS MarkSweep";
        GarbageCollector gc = GarbageCollector.nameOf(name);
        assertEquals(GarbageCollector.PSMarkSweep, gc);
    }

}
