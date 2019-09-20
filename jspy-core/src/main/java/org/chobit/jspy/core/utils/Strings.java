package org.chobit.jspy.core.utils;

import java.text.DecimalFormat;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Strings {


    /**
     * 判断字符串是否为空
     */
    public static boolean isBlank(final String s) {
        return s == null || s.trim().isEmpty();
    }


    /**
     * 判断字符串是否不为空
     */
    public static boolean isNotBlank(final String s) {
        return !isBlank(s);
    }


    /**
     * 将集合转为字符串, 默认分隔符为逗号
     */
    public static <T> String mkString(Iterable<T> itr) {
        return mkString(itr, ",");
    }


    /**
     * 将集合转为字符串
     */
    public static <T> String mkString(Iterable<T> itr, String separator) {
        StringBuilder builder = new StringBuilder();
        for (T t : itr) {
            if (builder.length() > 0) {
                builder.append(separator);
            }
            builder.append(t);
        }
        return builder.toString();
    }


    private static final Pattern HUMP_PATTERN = Pattern.compile("[A-Z]");

    /**
     * 驼峰格式转下划线
     */
    public static String humpToLine(String str) {
        Matcher matcher = HUMP_PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


    /**
     * 驼峰格式转下划线
     */
    public static String humpToLine2(String src) {
        return src.replaceAll("[A-Z]", "_$0").toLowerCase();
    }


    private static final Pattern LINE_PATTERN = Pattern.compile("_(\\w)");

    /**
     * 下划线转驼峰
     */
    public static String lineToHump(String src) {
        Objects.requireNonNull(src);

        src = src.toLowerCase();
        Matcher matcher = LINE_PATTERN.matcher(src);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 格式化长整型，格式如 1,000
     *
     * @param value 要格式化的值
     * @return 格式化后的值
     */
    public static String formatLong(long value) {
        return formatLong(",000", value);
    }

    /**
     * 格式化长整型
     *
     * @param pattern 格式模板
     * @param value   要格式化的值
     * @return 格式化后的值
     */
    public static String formatLong(String pattern, long value) {
        DecimalFormat format = new DecimalFormat(pattern);
        return format.format(value);
    }


    private Strings() {
    }
}
