package org.chobit.jspy.utils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Strings {


    public static boolean isBlank(final String s) {
        return s == null || s.trim().isEmpty();
    }


    public static boolean isNotBlank(final String s) {
        return !isBlank(s);
    }


    private static final Pattern HUMP_PATTERN = Pattern.compile("[A-Z]");
    public static String humpToLine(String str) {
        Matcher matcher = HUMP_PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String humpToLine2(String src) {
        return src.replaceAll("[A-Z]", "_$0").toLowerCase();
    }


    private static final Pattern LINE_PATTERN = Pattern.compile("_(\\w)");
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

}
