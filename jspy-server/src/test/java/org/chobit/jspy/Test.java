package org.chobit.jspy;


import org.apache.ibatis.jdbc.SQL;

import java.net.UnknownHostException;

public class Test {

    public static void main(String[] args) throws UnknownHostException {

        new SQL(){};

        new Test(){
            {
                f();
            }
        };

        System.out.println(java.net.InetAddress.getLocalHost().getHostAddress());
    }


    public int f(){
        return 1;
    }

}
