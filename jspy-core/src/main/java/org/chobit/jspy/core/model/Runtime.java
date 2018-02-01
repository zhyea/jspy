package org.chobit.jspy.core.model;

import java.util.List;

public class Runtime {

    /**
     * Runtime名称
     */
    private String name;

    /**
     * 当前进程ID
     */
    private int processId;

    /**
     * 应用启动时间
     */
    private long startTime;

    /**
     * 应用运行时长
     */
    private long upTime;

    private String specName;


    /**
     * 虚拟机名称
     */
    private String vmName;

    private String vmVendor;

    /**
     * 虚拟机版本号
     */
    private String vmVersion;

    private List<String> inputArguments;

    public Runtime(String name, int processId) {
        this.name = name;
        this.processId = processId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getUpTime() {
        return upTime;
    }

    public void setUpTime(long upTime) {
        this.upTime = upTime;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getVmName() {
        return vmName;
    }

    public void setVmName(String vmName) {
        this.vmName = vmName;
    }

    public String getVmVendor() {
        return vmVendor;
    }

    public void setVmVendor(String vmVendor) {
        this.vmVendor = vmVendor;
    }

    public String getVmVersion() {
        return vmVersion;
    }

    public void setVmVersion(String vmVersion) {
        this.vmVersion = vmVersion;
    }

    public List<String> getInputArguments() {
        return inputArguments;
    }

    public void setInputArguments(List<String> inputArguments) {
        this.inputArguments = inputArguments;
    }
}
