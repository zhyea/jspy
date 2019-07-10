package org.chobit.jspy.core.metrics;

public abstract class Snapshot {

    /**
     * 计算在指定比例的值
     *
     * @param quantile 一个指定的比例，取值在[0 ~ 1]
     * @return 计算出的在该比例的值
     */
    public abstract double getValue(double quantile);

    /**
     * 返回整个数值集合
     */
    public abstract long[] getValues();

    /**
     * 返回集合size
     */
    public abstract int size();

    /**
     * 返回中值，意味着集合中50%的值都小于改值
     */
    public double getMedian() {
        return getValue(0.5);
    }

    /**
     * 返回%75线，意味着集合中75%的值都小于改值
     */
    public double get75thPercentile() {
        return getValue(0.75);
    }

    /**
     * 返回%99线，意味着集合中99%的值都小于改值
     */
    public double get90thPercentile() {
        return getValue(0.90);
    }

    /**
     * 返回%95线，意味着集合中95%的值都小于改值
     */
    public double get95thPercentile() {
        return getValue(0.95);
    }

    /**
     * 返回%98线，意味着集合中98%的值都小于改值
     */
    public double get98thPercentile() {
        return getValue(0.98);
    }

    /**
     * 返回%99.9线，意味着集合中99.9%的值都小于改值
     */
    public double get999thPercentile() {
        return getValue(0.999);
    }

    /**
     * 返回最大值
     */
    public abstract long getMax();

    /**
     * 返回算数平均值（即均值）
     */
    public abstract double getMean();

    /**
     * 返回最小值
     */
    public abstract long getMin();

    /**
     * 返回标准差
     */
    public abstract double getStdDev();

}
