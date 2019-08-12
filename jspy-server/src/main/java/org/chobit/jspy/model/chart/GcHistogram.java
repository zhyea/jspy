package org.chobit.jspy.model.chart;

import org.chobit.jspy.charts.ChartType;
import org.chobit.jspy.charts.ValueType;
import org.chobit.jspy.charts.XAxisType;
import org.chobit.jspy.charts.annotation.Series;
import org.chobit.jspy.charts.annotation.XAxis;

import java.util.Date;

public class GcHistogram {


    @Series(value = "平均用时", unit = "ms", selected = false)
    private long mean;

    @Series(value = "用时", unit = "ms")
    private long sum;

    @Series(value = "次数", yAxisIndex = 1, type = ChartType.bar)
    private long count;

    @XAxis(type = XAxisType.time, valueType = ValueType.MILLS_TIME)
    private Date eventTime;


    public long getMean() {
        return mean;
    }

    public void setMean(long mean) {
        this.mean = mean;
    }

    public long getSum() {
        return this.sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }
}
