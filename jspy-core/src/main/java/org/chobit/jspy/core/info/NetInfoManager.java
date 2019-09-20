package org.chobit.jspy.core.info;

import java.net.UnknownHostException;

abstract class NetInfoManager {


    static String getLocalHostIp() {
        try {
            return java.net.InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "";
        }
    }

    static String getLocalHostName() {
        try {
            return java.net.InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "";
        }
    }


}
