package org.chobit.jspy.constants;


/**
 * 直方图类型
 */
public enum HistogramType {

    /**
     * 方法
     */
    METHOD(1),

    /**
     * GC
     */
    GC(2),
    ;

    public final int id;

    HistogramType(int id) {
        this.id = id;
    }
}
