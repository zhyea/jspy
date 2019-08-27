package org.chobit.jspy.jobs.internal;

import org.chobit.jspy.JSpyConfig;
import org.chobit.jspy.exception.JSpyConfigException;
import org.chobit.jspy.jobs.AbstractOneOffJob;
import org.chobit.jspy.jobs.AbstractQuartzJob;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.chobit.jspy.utils.Reflections.subTypeOf;

public class JSpyJobRegistry {


    private final JSpyConfig config;

    private final Map<Class<? extends AbstractQuartzJob>, AbstractQuartzJob> quartzJobMap;

    private final Map<Class<? extends AbstractOneOffJob>, AbstractOneOffJob> oneOffJobMap;


    public JSpyJobRegistry(JSpyConfig config) {
        this.config = config;
        try {
            quartzJobMap = quartzJobMap();
            oneOffJobMap = oneOffJobMap();
        } catch (Exception e) {
            throw new JSpyConfigException("obtain quartz job instance failed");
        }
    }


    /**
     * 获取quartz job实例集合
     */
    public Iterable<AbstractQuartzJob> quartzJobs() {
        return quartzJobMap.values();
    }


    /**
     * 获取one-off job实例集合
     */
    public Iterable<AbstractOneOffJob> oneOffJobs() {
        return oneOffJobMap.values();
    }


    /**
     * 根据类名获取对应获取job实例
     */
    public AbstractQuartzJob getQuartzJobInstance(Class<? extends AbstractQuartzJob> jobClass) {
        if (quartzJobMap.containsKey(jobClass)) {
            return quartzJobMap.get(jobClass);
        } else {
            throw new JSpyConfigException("Cannot get job instance with class:" + jobClass);
        }
    }


    private Map<Class<? extends AbstractQuartzJob>, AbstractQuartzJob> quartzJobMap()
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Set<Class<? extends AbstractQuartzJob>> jobClasses = subTypeOf(AbstractQuartzJob.class);
        Map<Class<? extends AbstractQuartzJob>, AbstractQuartzJob> map = new HashMap<>(jobClasses.size());
        for (Class<? extends AbstractQuartzJob> clazz : jobClasses) {
            AbstractQuartzJob instance = clazz.getDeclaredConstructor(JSpyConfig.class).newInstance(config);
            map.put(clazz, instance);
        }
        return map;
    }


    private Map<Class<? extends AbstractOneOffJob>, AbstractOneOffJob> oneOffJobMap()
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Set<Class<? extends AbstractOneOffJob>> jobClasses = subTypeOf(AbstractOneOffJob.class);
        Map<Class<? extends AbstractOneOffJob>, AbstractOneOffJob> map = new HashMap<>(jobClasses.size());
        for (Class<? extends AbstractOneOffJob> clazz : jobClasses) {
            AbstractOneOffJob instance = clazz.getDeclaredConstructor(JSpyConfig.class).newInstance(config);
            map.put(clazz, instance);
        }
        return map;
    }


}
