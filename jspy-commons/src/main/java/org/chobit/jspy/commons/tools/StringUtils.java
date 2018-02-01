package org.chobit.jspy.commons.tools;

import java.util.concurrent.RejectedExecutionException;

public class StringUtils {


    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }


    private StringUtils() {
        throw new RejectedExecutionException("cannot use private constructor.");
    }

}
