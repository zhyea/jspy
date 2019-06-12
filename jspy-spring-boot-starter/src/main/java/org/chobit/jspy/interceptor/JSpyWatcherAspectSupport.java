package org.chobit.jspy.interceptor;

import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;

public abstract class JSpyWatcherAspectSupport implements BeanFactoryAware, InitializingBean {


    private JSpyWatcherAttributeSource attrSource;


    public JSpyWatcherAttributeSource getAttrSource() {
        return attrSource;
    }

    public void setAttrSource(JSpyWatcherAttributeSource attrSource) {
        this.attrSource = attrSource;
    }


    protected Object invoke(Method method, Class<?> targetClass, final InvocationCallback invocation) throws Throwable {
        final JSpyWatcherAttribute attr = getAttrSource().getJSpyWatcherAttribute(method, targetClass);
        final String methodId = methodIdentity(method, targetClass, attr);
        Object r = null;
        try {
            r = invocation.proceedWithInvocation();
        } catch (Throwable t) {
            // TODO
            throw t;
        } finally {
            // TODO
        }
        return r;
    }


    private String methodIdentity(Method method, Class<?> targetClass, JSpyWatcherAttribute attr) {
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
