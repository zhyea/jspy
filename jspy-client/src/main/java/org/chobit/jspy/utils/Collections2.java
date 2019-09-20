package org.chobit.jspy.utils;

import org.chobit.jspy.core.utils.Strings;

/**
 * 集合处理类
 */
public abstract class Collections2 {

    /**
     * 判断字符串集合是否为空
     */
    public static boolean isNotAllBlank(Iterable<String> itr) {
        if (null == itr) {
            return false;
        }

        for (String s : itr) {
            if (Strings.isNotBlank(s)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断字符串集合是否为空
     */
    public static boolean isAllBlank(Iterable<String> itr) {
        return !isNotAllBlank(itr);
    }


    private Collections2() {
    }

}
