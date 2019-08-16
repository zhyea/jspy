package org.chobit.jspy.service.entity;

public abstract class AbstractStatEntity extends AbstractEntity {

    private String appCode;

    private String ip;

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
