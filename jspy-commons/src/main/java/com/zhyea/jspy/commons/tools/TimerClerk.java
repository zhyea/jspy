package com.zhyea.jspy.commons.tools;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static com.zhyea.jspy.commons.tools.MD5.md5;

/**
 * 计时管理类
 */
public class TimerClerk {

    private static final ConcurrentHashMap<String, AtomicLong> counter = new ConcurrentHashMap<>();


    public static void add(String name, long executeMills) {
        String id = md5(name);


    }


}