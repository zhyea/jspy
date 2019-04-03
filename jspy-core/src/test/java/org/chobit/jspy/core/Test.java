package org.chobit.jspy.core;

import org.hyperic.sigar.SysInfo;

public class Test {

    public static void main(String[] args) {
        SysInfo info = new SysInfo();
        System.out.println(info.getName());
        System.out.println(info.getVendor());
    }


}
