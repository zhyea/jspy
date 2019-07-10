package org.chobit.jspy.core.metrics;


/**
 * 用来进行统计的数据集接口
 */
public interface Reservoir {

    /**
     * 返回结果集总数
     */
    int size();


    /**
     * 添加一条新的数据
     */
    void update(long value);


    /**
     * 获取当前结果集快照
     */
    Snapshot getSnapshot();

}