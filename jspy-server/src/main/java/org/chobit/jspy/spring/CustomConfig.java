package org.chobit.jspy.spring;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "custom")
public class CustomConfig {

    /**
     * 数据保留的时间，超过该时间后，指标数据会全部被删除（包括shrink后的数据）
     */
    private int dataReserveDates;

    /**
     * shrink操作开始时间，即数据保留该时间后即会被shrink
     */
    private int shrinkStartDates;

    public int getDataReserveDates() {
        return dataReserveDates;
    }

    public void setDataReserveDates(int dataReserveDates) {
        this.dataReserveDates = dataReserveDates;
    }

    public int getShrinkStartDates() {
        return shrinkStartDates;
    }

    public void setShrinkStartDates(int shrinkStartDates) {
        this.shrinkStartDates = shrinkStartDates;
    }
}
