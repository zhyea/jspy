package org.chobit.jspy;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import org.chobit.jspy.core.annotation.JSpyWatcher;
import org.chobit.jspy.core.support.JSpyWatcherCollector;
import org.chobit.jspy.utils.SysTime;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

import static org.chobit.jspy.core.utils.Strings.isBlank;

/**
 * Watcher拦截器
 */
public class WatcherInterceptor {

    /**
     * Watcher实例，用于搜集方法执行计时数据
     */
    private static JSpyWatcherCollector collector = JSpyWatcherCollector.build();

    /**
     * 方法ID集合，用于快速的获取在@JSpyWatcher注解中配置的方法名称
     */
    private static Map<String, String> methodIds = new ConcurrentHashMap<>(32);


    /**
     * 执行方法拦截逻辑
     */
    @RuntimeType
    public static Object intercept(@Origin Method method,
                                   @SuperCall Callable<?> callable) throws Exception {
        String methodId = getMethodId(method);
        long start = System.currentTimeMillis();
        try {
            return callable.call();
        } catch (Throwable t) {
            collector.updateFailed(methodId, SysTime.millis() - start);
            throw t;
        } finally {
            collector.update(methodId, SysTime.millis() - start);
        }
    }


    /**
     * 获取方法ID
     *
     * @param method Method实例
     * @return 方法ID
     */
    private static String getMethodId(Method method) {
        String key = method.toGenericString();
        String name = methodIds.get(key);
        if (null == name) {
            JSpyWatcher w = method.getAnnotation(JSpyWatcher.class);
            name = w.value();
            if (isBlank(name)) {
                name = key;
            }
            methodIds.put(key, name);
        }
        return name;
    }
}
