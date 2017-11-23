package com.zhyea.jspy.commons.tools;

import com.zhyea.jspy.commons.model.TimerEntry;

import java.util.concurrent.ConcurrentHashMap;

import static com.zhyea.jspy.commons.tools.MD5.md5;

/**
 * 计时管理类
 */
public class TimerClerk {

    private static final ConcurrentHashMap<String, TimerEntry> timerBook = new ConcurrentHashMap<>();


    /**
     * 累加记录
     */
    public static void add(String name, long executeMills) {
        String id = md5(name);
        TimerEntry entry = timerBook.get(id);
        if (null == entry) {
            entry = new TimerEntry(id, name);
            TimerEntry oldRecord = timerBook.putIfAbsent(id, entry);
            entry = oldRecord;
        }
        entry.getCount().incrementAndGet();
        entry.getTotalUsedTime().addAndGet(executeMills);
    }


}