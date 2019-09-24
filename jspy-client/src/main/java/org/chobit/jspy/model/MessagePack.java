package org.chobit.jspy.model;

import org.chobit.jspy.core.model.Item;
import org.chobit.jspy.utils.SysTime;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MessagePack {

    private List<ClassLoadingCount> classes = new LinkedList<>();

    private Map<Long, Double> cpuUsage = new LinkedHashMap<>(64);

    private List<GcOverview> gc = new LinkedList<>();

    private List<MemoryOverview> memories = new LinkedList<>();

    private List<MethodHistogram> methods = new LinkedList<>();

    private List<ThreadOverview> threads = new LinkedList<>();

    private List<Item> runtime;

    private List<Item> sysInfo;


    public void addClass(ClassLoadingCount cl) {
        if (null != cl) {
            this.classes.add(cl);
        }
    }

    public void addCpuUsage(Double usage) {
        if (null != usage) {
            this.cpuUsage.put(SysTime.millis(), usage);
        }
    }

    public void addGc(GcOverview gc) {
        if (null != gc) {
            this.gc.add(gc);
        }
    }

    public void addMemory(MemoryOverview mem) {
        if (null != mem) {
            this.memories.add(mem);
        }
    }

    public void addMethod(MethodHistogram mh) {
        if (null != mh) {
            this.methods.add(mh);
        }
    }

    public void addThread(ThreadOverview thread) {
        if (null != thread) {
            this.threads.add(thread);
        }
    }

    /* ------------------------ Get & Set ------------------------ */

    public List<ClassLoadingCount> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassLoadingCount> classes) {
        this.classes = classes;
    }

    public Map<Long, Double> getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(Map<Long, Double> cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public List<GcOverview> getGc() {
        return gc;
    }

    public void setGc(List<GcOverview> gc) {
        this.gc = gc;
    }

    public List<MemoryOverview> getMemories() {
        return memories;
    }

    public void setMemories(List<MemoryOverview> memories) {
        this.memories = memories;
    }

    public List<MethodHistogram> getMethods() {
        return methods;
    }

    public void setMethods(List<MethodHistogram> methods) {
        this.methods = methods;
    }

    public List<ThreadOverview> getThreads() {
        return threads;
    }

    public void setThreads(List<ThreadOverview> threads) {
        this.threads = threads;
    }

    public List<Item> getRuntime() {
        return runtime;
    }

    public void setRuntime(List<Item> runtime) {
        this.runtime = runtime;
    }

    public List<Item> getSysInfo() {
        return sysInfo;
    }

    public void setSysInfo(List<Item> sysInfo) {
        this.sysInfo = sysInfo;
    }
}
