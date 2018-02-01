package org.chobit.jspy.commons.tools;

import org.chobit.jspy.commons.model.TimerEntry;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RejectedExecutionException;

import static org.chobit.jspy.commons.tools.MD5.md5;

/**
 * 计时管理类
 */
public class TimerClerk {

    private static final ConcurrentHashMap<String, TimerEntry> timerBook = new ConcurrentHashMap<>();


    /**
     * 累加记录
     */
    public static void add(String name, String desc, long executeMills) {
        String id = md5(name + desc);
        TimerEntry entry = timerBook.get(id);
        if (null == entry) {
            entry = new TimerEntry(id, name, desc);
            TimerEntry oldRecord = timerBook.putIfAbsent(id, entry);
            if (null != oldRecord) {
                entry = oldRecord;
            }
        }
        entry.getCount().incrementAndGet();
        entry.getUsedTime().addAndGet(executeMills);
    }


    public static Collection<TimerEntry> all() {
        return timerBook.values();
    }



    private TimerClerk() {
        throw new RejectedExecutionException("cannot use private constructor.");
    }
}