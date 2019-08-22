package org.chobit.jspy;


import org.chobit.jspy.core.info.Sys;

import static org.chobit.jspy.utils.JSON.toJson;

public class Test {


    public static void main(String[] args) {
        System.out.println(toJson(Sys.values()));
    }


}
