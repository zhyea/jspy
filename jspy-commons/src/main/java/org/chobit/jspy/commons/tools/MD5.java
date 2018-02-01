package org.chobit.jspy.commons.tools;

import java.security.MessageDigest;
import java.util.concurrent.RejectedExecutionException;

public final class MD5 {


    public static String md5(String src) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(src.getBytes("UTF-8"));
            return toHex(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private static String toHex(byte[] bytes) {
        StringBuffer md5str = new StringBuffer();
        for (int digital : bytes) {
            if (digital < 0) {
                digital += 256;
            }
            if (digital < 16) {
                md5str.append("0");
            }
            md5str.append(Integer.toHexString(digital));
        }
        return md5str.toString().toUpperCase();
    }


    private MD5() {
        throw new RejectedExecutionException("cannot use private constructor.");
    }
}
