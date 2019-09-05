package org.chobit.jspy.tools;

import java.util.Arrays;
import java.util.LinkedList;

public class Counter {

    private final LinkedList<Double> values = new LinkedList<>();

    public void add(double value) {
        values.add(value);
    }


    /**
     * 计算一个数据集的浮动区间
     * <p>
     * 取数据集排序后，20%~80%这个区间内的数据的平均值作为浮动区间
     */
    public double computeFloatingRange() {
        Double[] arr = new Double[values.size()];
        values.toArray(arr);
        Arrays.sort(arr);

        int lowIdx = Double.valueOf(0.2 * arr.length).intValue();
        int highIdx = Double.valueOf(0.8 * arr.length).intValue();

        int len = highIdx - lowIdx + 1;

        Double[] arr0 = new Double[len];
        System.arraycopy(arr, lowIdx, arr0, 0, len);

        return Arrays.stream(arr0).mapToDouble(e -> e).sum() / len;
    }


}
