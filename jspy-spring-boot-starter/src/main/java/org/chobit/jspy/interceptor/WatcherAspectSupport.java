package org.chobit.jspy.interceptor;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;

public abstract class WatcherAspectSupport implements BeanFactoryAware, InitializingBean {


    private WatcherAttributeSource attrSource;

    @Nullable
    private BeanFactory beanFactory;


    @Override
    public void setBeanFactory(@Nullable BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Nullable
    protected final BeanFactory getBeanFactory() {
        return this.beanFactory;
    }


    public WatcherAttributeSource getAttrSource() {
        return attrSource;
    }

    public void setAttrSource(WatcherAttributeSource attrSource) {
        this.attrSource = attrSource;
    }


    @Override
    public void afterPropertiesSet() {
        if (this.beanFactory == null) {
            throw new IllegalStateException(
                    "Set the 'transactionManager' property or make sure to run within a BeanFactory " +
                            "containing a PlatformTransactionManager bean!");
        }
        if (getAttrSource() == null) {
            throw new IllegalStateException(
                    "Either 'transactionAttributeSource' or 'transactionAttributes' is required: " +
                            "If there are no transactional methods, then don't use a transaction aspect.");
        }
    }



    protected Object invoke(Method method, Class<?> targetClass, final InvocationCallback invocation) throws Throwable {
        final WatcherAttribute attr = getAttrSource().getWatcherAttribute(method, targetClass);
        final String methodId = methodIdentity(method, targetClass, attr);
        Object r = null;
        try {
            r = invocation.proceedWithInvocation();
        } catch (Throwable t) {
            // TODO
            throw t;
        } finally {
            System.out.println("---------------------invoke>>>>>" + methodId);
            // TODO
        }
        return r;
    }


    private String methodIdentity(Method method, Class<?> targetClass, WatcherAttribute attr) {
        String methodIdentity = attr.getDescriptor();
        if (null == methodIdentity) {
            methodIdentity = ClassUtils.getQualifiedMethodName(method, targetClass);
        }
        return methodIdentity;
    }


    protected interface InvocationCallback {
        Object proceedWithInvocation() throws Throwable;
    }
}
