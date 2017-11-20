package com.zhyea.jspy.commons.tools;

import java.security.MessageDigest;

public final class StringKit {

    public static String md5(String src) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            byte[] bytes = md5.digest(src.getBytes());
            return toHex(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toHex(byte[] bytes) {
        StringBuffer md5str = new StringBuffer();
        int digital;
        for (int i = 0; i < bytes.length; i++) {
            digital = bytes[i];
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
}
