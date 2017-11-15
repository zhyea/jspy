package com.zhyea.jspy.agent.tools;

import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class Watcher implements Closeable, Cloneable {
    /**
     * 计时统计注册表
     */
    public static Map<String, WatchUnit> counter;
    /**
     * 当前计时任务名称
     */
    private String name;
    /**
     * 标志是否开始
     */
    private boolean isStarted;
    /**
     * 计时开始的时间
     */
    private long startNanos;
    /**
     * 当前已经经过的时间
     */
    private long currentElapsedNanos;
    /**
     * 当前的计时单位
     */
    private TimeUnit timeUnit;
    /**
     * 用来为clone使用
     */
    private static Watcher watcher = new Watcher();

    static {
        counter = new HashMap<String, WatchUnit>();
    }

    /**
     * 构造器
     */
    public Watcher() {
    }

    public Watcher(String name) {
        this.name = name;
    }

    public Watcher(String name, TimeUnit timeUnit) {
        this(name);
        this.timeUnit = timeUnit;
    }

    /**
     * 用来判断Watcher是否已经启动的方法
     *
     * @return 如Watcher已经启动，返回true；否则，返回 false
     */
    public boolean isRunning() {
        return isStarted;
    }

    /**
     * 开始时间计量并调整运行标志
     *
     * @return Watcher实例
     */
    public Watcher start() {
        if (isStarted) {
            throw new IllegalStateException("Watcher is already running");
        }
        isStarted = true;
        startNanos = System.nanoTime();
        return this;
    }

    /**
     * 停止时间计量并调整运行标志
     *
     * @return Watcher实例
     */
    public Watcher stop() {
        if (!isStarted) {
            throw new IllegalStateException("Watcher is already stopped");
        }
        long now = System.nanoTime();
        isStarted = false;
        currentElapsedNanos += now - startNanos;
        return this;
    }

    /**
     * 重置经过的时间为0，并将运行标志调整为false。
     *
     * @return Watcher实例
     */
    public Watcher reset() {
        currentElapsedNanos = 0;
        isStarted = false;
        return this;
    }

    /**
     * 以指定的TimeUnit计算当前经过的时间
     *
     * @return 当前已经经过的时间
     */
    public long now(TimeUnit timeUnit) {
        return timeUnit.convert(now(), TimeUnit.NANOSECONDS);
    }

    /**
     * 当前已经经过的时间，单位纳秒
     */
    public long now() {
        return isStarted ? System.nanoTime() - startNanos + currentElapsedNanos : currentElapsedNanos;
    }

    /**
     * 将统计信息转为字符串
     */
    @Override
    public String toString() {
        return String.valueOf(now());
    }

    @Override
    public void close() {
        if (isStarted) {
            stop();
        }
        if (null == timeUnit) {
            add(now());
        } else {
            add(now(timeUnit));
        }
    }

    /**
     * 设置监控名称
     *
     * @param name 监控名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 设置当前的计时单位
     *
     * @param timeUnit 计时单位
     */
    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    /**
     * 累计监控时间
     *
     * @param elapseTime 监控时间
     */
    private void add(long elapseTime) {
        WatchUnit wu = counter.get(name);
        if (null == wu) {
            wu = new WatchUnit(name);
        }
        wu.add(elapseTime);
        counter.put(name, wu);
    }

    /**
     * 展示所有的结果
     */
    public static void dump() {
        for (String key : counter.keySet()) {
            System.out.println(counter.get(key).toString());
        }
        counter.clear();
    }

    /**
     * clone Watcher对象
     */
    @Override
    public Watcher clone() {
        Watcher watcher = null;
        try {
            watcher = (Watcher) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return watcher;
    }

    /**
     * 基于原型模式获取计时对象
     */
    public static Watcher getWatcher() {
        if (null == watcher) {
            watcher = new Watcher();
        }
        return watcher.clone();
    }

    /**
     * 监控单元
     */
    class WatchUnit {
        /**
         * 监控单元
         */
        private String name;
        /**
         * 监控内容执行次数
         */
        private int count = 0;
        /**
         * 监控方法执行的时间
         */
        private Long totalElapseTime = 0L;

        WatchUnit(String name) {
            this.name = name;
        }

        public void add(long elapseTime) {
            this.count++;
            this.totalElapseTime += elapseTime;
        }

        @Override
        public String toString() {
            return name + "-------------" + "执行了" + count + "次;" + "共耗时" + totalElapseTime + "; 平均" + (totalElapseTime / count);
        }
    }
}