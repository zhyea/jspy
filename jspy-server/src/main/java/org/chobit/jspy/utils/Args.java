package org.chobit.jspy.utils;

import org.chobit.jspy.exceptions.JSpyArgsException;

import static org.chobit.jspy.core.utils.Strings.isBlank;


public abstract class Args {

    public static void check(boolean result, String errMsg) {
        if (result) {
            throw new JSpyArgsException(errMsg);
        }
    }

    public static void checkNotNull(Object src, String errMsg) {
        check(null == src, errMsg);
    }

    public static void checkNull(Object src, String errMsg) {
        check(null != src, errMsg);
    }


    public static void checkNotBlank(String src, String errMsg) {
        check(isBlank(src), errMsg);
    }


    private Args() {
        throw new UnsupportedOperationException("Private constructor, cannot be accessed.");
    }

}
