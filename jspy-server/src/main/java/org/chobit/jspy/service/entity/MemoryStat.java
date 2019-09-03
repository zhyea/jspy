package org.chobit.jspy.service.entity;

import org.chobit.jspy.charts.annotation.Series;
import org.chobit.jspy.core.model.MemoryInfo;

import java.lang.management.MemoryType;
import java.util.Arrays;

import static org.chobit.jspy.charts.ValueType.STORAGE;

public class MemoryStat extends AbstractStatEntity {


    private String type;

    private String name;

    private String managerNames;

    @Series(value = "初始内存", valType = STORAGE, selected = false)
    private long init;

    @Series(value = "已使用", valType = STORAGE)
    private long used;

    @Series(value = "已提交", valType = STORAGE)
    private long committed;

    @Series(value = "最大可用", valType = STORAGE, selected = false)
    private long max;

    private int isPeak = 0;


    public MemoryStat() {
    }

    public MemoryStat(String appCode,
                      String ip,
                      MemoryType type,
                      MemoryInfo usage,
                      String name,
                      String[] managerNames,
                      long eventTime,
                      boolean isPeak) {
        this.setType(type.name());
        this.setName(name);
        this.setManagerNames(Arrays.toString(managerNames));
        this.setIp(ip);
        this.setAppCode(appCode);
        this.setInit(usage.getInit());
        this.setUsed(usage.getUsed());
        this.setCommitted(usage.getCommitted());
        this.setMax(usage.getMax());
        this.setEventTime(eventTime);
        this.setIsPeak(isPeak ? 1 : 0);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerNames() {
        return managerNames;
    }

    public void setManagerNames(String managerNames) {
        this.managerNames = managerNames;
    }

    public long getInit() {
        return init;
    }

    public void setInit(long init) {
        this.init = init;
    }

    public long getUsed() {
        return used;
    }

    public void setUsed(long used) {
        this.used = used;
    }

    public long getCommitted() {
        return committed;
    }

    public void setCommitted(long committed) {
        this.committed = committed;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }

    public int getIsPeak() {
        return isPeak;
    }

    public void setIsPeak(int isPeak) {
        this.isPeak = isPeak;
    }
}
