package org.chobit.jspy.interceptor;

import org.chobit.jspy.WatcherConfig;
import org.chobit.jspy.core.support.JSpyWatcherCollector;
import org.chobit.jspy.utils.SysTime;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;

import static org.chobit.jspy.core.support.JSpyWatcherCollector.createIfNon;
import static org.chobit.jspy.core.utils.Strings.isBlank;
import static org.chobit.jspy.core.utils.Strings.isNotBlank;

public abstract class WatcherAspectSupport implements BeanFactoryAware, InitializingBean {


    private WatcherAttributeSource attrSource;

    @Nullable
    private BeanFactory beanFactory;

    private JSpyWatcherCollector collector;

    public WatcherAspectSupport(WatcherConfig config) {
        collector = createIfNon(config.getHistogramPeriod(), config.getExpectNumMethods());
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
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
        if (null == this.beanFactory) {
            throw new IllegalStateException("Make sure to run within a BeanFactory containing a WatcherInterceptor bean!");
        }
        if (null == getAttrSource()) {
            throw new IllegalStateException(
                    "'watcherAttributeSource' is required: If there are no 'watcherAttributeSource', then don't use a jspy watcher aspect.");
        }
    }


    protected Object watcherInvoke(Method method, Class<?> targetClass, final InvocationCallback invocation) throws Throwable {
        final WatcherAttribute attr = getAttrSource().getWatcherAttribute(method, targetClass);
        final String methodId = methodIdentity(method, targetClass, attr);
        Object r;
        long start = SysTime.millis();
        try {
            r = invocation.proceedWithInvocation();
        } catch (Throwable t) {
            collector.updateFailed(methodId, SysTime.millis() - start);
            throw t;
        } finally {
            collector.update(methodId, SysTime.millis() - start);
        }
        return r;
    }


    private String methodIdentity(Method method, Class<?> targetClass, WatcherAttribute attr) {
        if (isNotBlank(attr.getName())) {
            return attr.getName();
        }
        String methodIdentity = attr.getMethodIdentity();
        if (isBlank(methodIdentity)) {
            methodIdentity = ClassUtils.getQualifiedMethodName(method, targetClass);
        }
        return methodIdentity;
    }


    protected interface InvocationCallback {
        Object proceedWithInvocation() throws Throwable;
    }
}
