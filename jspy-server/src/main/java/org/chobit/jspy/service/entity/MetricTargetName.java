package org.chobit.jspy.service.entity;

import org.chobit.jspy.constants.MetricTarget;

public class MetricTargetName extends AbstractEntity {


    private String appCode;

    private MetricTarget target;

    private String name;


    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public MetricTarget getTarget() {
        return target;
    }

    public void setTarget(MetricTarget target) {
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
