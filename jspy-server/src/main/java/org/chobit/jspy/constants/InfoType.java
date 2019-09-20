package org.chobit.jspy.constants;


/**
 * INFO 类型
 */
public enum InfoType {


    SYS(1),

    RUNTIME(2),
    ;
    public final int id;

    InfoType(int id) {
        this.id = id;
    }
}
