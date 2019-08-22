package org.chobit.jspy.core.gauge;

import org.chobit.jspy.core.model.Item;

import java.util.List;

import static sun.plugin2.util.PojoUtil.toJson;


public class Sys {


    public static void main(String[] args) {
        List<Item> items = org.chobit.jspy.core.info.Sys.values();
        System.out.println(toJson(items));
    }
}
