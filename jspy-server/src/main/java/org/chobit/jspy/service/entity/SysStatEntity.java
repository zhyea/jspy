package org.chobit.jspy.service.entity;

import java.util.Date;

public class SysStatEntity extends AbstractStatEntity {

    private String detail;

    private Date eventTime;


    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }
}
