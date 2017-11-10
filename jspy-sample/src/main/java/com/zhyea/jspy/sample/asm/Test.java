package com.zhyea.jspy.sample.asm;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

        Class clazz = Account.class;
        System.out.println(clazz.getCanonicalName());
        System.out.println(clazz.getName());
        System.out.println(clazz.getPackage());
        System.out.println(clazz.getProtectionDomain());
        System.out.println(clazz.getSimpleName());
        System.out.println(clazz.getDeclaringClass());
        System.out.println(Arrays.toString(clazz.getDeclaredAnnotations()));
        System.out.println(Arrays.toString(clazz.getDeclaredFields()));
    }


}
