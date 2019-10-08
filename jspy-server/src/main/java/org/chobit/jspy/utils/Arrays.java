package org.chobit.jspy.utils;

import java.util.LinkedList;
import java.util.List;

public abstract class Arrays {


    public static String[] merge(String[] ... arrays){
        List<String> list = new LinkedList<>();
        for(String[] arr : arrays){
            list.addAll(java.util.Arrays.asList(arr));
        }
        return list.toArray(new String[0]);
    }


    private Arrays() {
    }

}
