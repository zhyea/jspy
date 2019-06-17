package org.chobit.jspy.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.AopUtils;

import java.io.Serializable;

public class JSpyWatcherInterceptor extends JSpyWatcherAspectSupport implements MethodInterceptor, Serializable {


    public JSpyWatcherInterceptor() {
    }


    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Class<?> targetClass = (invocation.getThis() != null ? AopUtils.getTargetClass(invocation.getThis()) : null);
        return super.invoke(invocation.getMethod(), targetClass, invocation::proceed);
    }
}
