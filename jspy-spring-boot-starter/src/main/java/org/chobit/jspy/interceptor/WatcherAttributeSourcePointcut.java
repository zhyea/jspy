package org.chobit.jspy.interceptor;


import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.lang.reflect.Method;

abstract class WatcherAttributeSourcePointcut extends StaticMethodMatcherPointcut implements Serializable {


    @Override
    public boolean matches(Method method, @Nullable Class<?> targetClass) {
        WatcherAttributeSource was = getWatcherAttributeSource();
        return (null == was || null != was.getWatcherAttribute(method, targetClass));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WatcherAttributeSourcePointcut)) {
            return false;
        }
        WatcherAttributeSourcePointcut otherPc = (WatcherAttributeSourcePointcut) other;
        return ObjectUtils.nullSafeEquals(getWatcherAttributeSource(), otherPc.getWatcherAttributeSource());
    }

    @Override
    public int hashCode() {
        return WatcherAttributeSourcePointcut.class.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getName() + ": " + getWatcherAttributeSource();
    }


    @Nullable
    protected abstract WatcherAttributeSource getWatcherAttributeSource();

}
