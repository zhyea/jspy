package org.chobit.jspy.interceptor;

import java.lang.reflect.Method;

public interface WatcherAttributeSource {

    WatcherAttribute getWatcherAttribute(Method method, Class<?> targetClass);

}
