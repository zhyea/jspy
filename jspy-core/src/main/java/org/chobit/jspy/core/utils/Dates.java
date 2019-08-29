package org.chobit.jspy.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Dates {


    public static final String PATTERN_DATE = "yyyyMMdd";

    public static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:SS";


    /**
     * 格式化为日期，格式：yyyyMMdd
     */
    public static String formatDate(Date date) {
        return format(PATTERN_DATE, date);
    }


    /**
     * 格式化日期时间，格式：yyyy-MM-dd HH:mm:SS
     */
    public static String format(long millis) {
        return format(PATTERN_DATETIME, millis);
    }


    /**
     * 格式化日期
     */
    public static String format(String pattern, long millis) {
        return format(pattern, new Date(millis));
    }

    /**
     * 格式化日期时间，格式：yyyy-MM-dd HH:mm:SS
     */
    public static String format(Date date) {
        return format(PATTERN_DATETIME, date);
    }


    /**
     * 格式化日期
     */
    public static String format(String pattern, Date date) {
        return new SimpleDateFormat(pattern).format(date);
    }


}
