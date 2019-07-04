package org.chobit.jspy.jobs.internal;

import org.chobit.jspy.JSpyConfig;
import org.chobit.jspy.exception.JSpyConfigException;
import org.chobit.jspy.jobs.JobCapsule;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.chobit.jspy.utils.Reflections.subTypeOf;

public class JSpyJobProxy {


    private final JSpyConfig config;

    private final Map<Class<? extends JobCapsule>, JobCapsule> jobCapsuleMap;

    public JSpyJobProxy(JSpyConfig config) {
        this.config = config;
        try {
            jobCapsuleMap = jobCapsuleMap();
        } catch (Exception e) {
            throw new JSpyConfigException("obtain quartz job instance failed");
        }
    }


    /**
     * 获取quartz job实例集合
     */
    public Iterable<JobCapsule> jobs() {
        return jobCapsuleMap.values();
    }


    /**
     * 根据类名获取对应获取job实例
     */
    public JobCapsule getInstance(Class<? extends JobCapsule> jobClass) {
        if (jobCapsuleMap.containsKey(jobClass)) {
            return jobCapsuleMap.get(jobClass);
        } else {
            throw new JSpyConfigException("Cannot get job instance with class:" + jobClass);
        }
    }



    private Map<Class<? extends JobCapsule>, JobCapsule> jobCapsuleMap()
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Set<Class<? extends JobCapsule>> jobClasses = subTypeOf(JobCapsule.class);
        Map<Class<? extends JobCapsule>, JobCapsule> map = new HashMap<>(jobClasses.size());
        for (Class<? extends JobCapsule> clazz : jobClasses) {
            JobCapsule instance = clazz.getDeclaredConstructor(JSpyConfig.class).newInstance(config);
            map.put(clazz, instance);
        }
        return map;
    }


}
