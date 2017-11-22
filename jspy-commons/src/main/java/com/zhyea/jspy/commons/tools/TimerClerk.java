package com.zhyea.jspy.commons.tools;

import com.zhyea.jspy.commons.model.TimerRecord;

import java.util.concurrent.ConcurrentHashMap;

import static com.zhyea.jspy.commons.tools.MD5.md5;

/**
 * 计时管理类
 */
public class TimerClerk {

    private static final ConcurrentHashMap<String, TimerRecord> counter = new ConcurrentHashMap<>();


    public static void add(String name, long executeMills) {
        String id = md5(name);
        if (!counter.containsKey(id)) {
            TimerRecord record = new TimerRecord(id, name);
            counter.putIfAbsent(id, record);
        }
    }




}