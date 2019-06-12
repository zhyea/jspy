package org.chobit.jspy.interceptor;

import java.lang.reflect.Method;

public interface JSpyWatcherAttributeSource {

    JSpyWatcherAttribute getJSpyWatcherAttribute(Method method, Class<?> targetClass);

}
