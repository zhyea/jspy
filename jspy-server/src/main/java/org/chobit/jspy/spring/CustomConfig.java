package org.chobit.jspy.spring;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "custom")
public class CustomConfig {


    private int dataReserveDates;

    public int getDataReserveDates() {
        return dataReserveDates;
    }

    public void setDataReserveDates(int dataReserveDates) {
        this.dataReserveDates = dataReserveDates;
    }
}
