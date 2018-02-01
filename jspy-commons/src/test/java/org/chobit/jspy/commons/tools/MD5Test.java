package org.chobit.jspy.commons.tools;

import junit.framework.TestCase;

import static org.chobit.jspy.commons.tools.MD5.md5;


public class MD5Test extends TestCase {


    public void testMd5() {
        String src = "f7d7352f863b41698cecce60ea2ec80020150904002015090423";
        String expected = "1713E948CF1612D05B9B076B9FAF4522";
        assertEquals(expected, md5(src));
    }


}
