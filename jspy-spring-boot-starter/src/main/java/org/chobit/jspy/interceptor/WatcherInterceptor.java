package org.chobit.jspy.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.AopUtils;

import java.io.Serializable;

public class WatcherInterceptor extends WatcherAspectSupport implements MethodInterceptor, Serializable {


    public WatcherInterceptor() {
    }


    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Class<?> targetClass = (invocation.getThis() != null ? AopUtils.getTargetClass(invocation.getThis()) : null);
        return super.invoke(invocation.getMethod(), targetClass, invocation::proceed);
    }
}
