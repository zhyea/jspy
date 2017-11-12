package com.zhyea.jspy.sample;

import com.zhyea.jspy.sample.asm.Account;

import java.util.Arrays;

public class Test {




    //http://blog.csdn.net/wwwxxdddx/article/details/51878772
    //http://blog.csdn.net/wwwxxdddx/article/details/45064219


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
