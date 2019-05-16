package org.chobit.jspy.core.constants;

import junit.framework.TestCase;

import static org.chobit.jspy.core.constants.StorageUnit.B;

public class StorageUnitTest extends TestCase {

    public void testCompute() {
        long size = 1024 * 1024 * 1024;
        assertEquals(1024 * 1024, B.toKB(size));
    }

}
