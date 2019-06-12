package org.chobit.jspy.interceptor;

import org.chobit.jspy.core.annotation.JSpyWatcher;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.MethodClassKey;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AnnotationJSpyWatcherAttributeSource implements JSpyWatcherAttributeSource {


    private final static JSpyWatcherAttribute NULL_ATTRIBUTE = new JSpyWatcherAttribute(null);

    private final Map<Object, JSpyWatcherAttribute> attrCache =
            new ConcurrentHashMap<>(1024);

    @Override
    public JSpyWatcherAttribute getJSpyWatcherAttribute(Method method, Class<?> targetClass) {

        if (method.getDeclaringClass() == Object.class) {
            return null;
        }

        Object cacheKey = getCacheKey(method, targetClass);
        JSpyWatcherAttribute cached = this.attrCache.get(cacheKey);

        if (null != cached) {
            return cached;
        } else {
            JSpyWatcherAttribute attr = computeJSpyWatcherAttribute(method, targetClass);
            if (null == attr) {
                this.attrCache.put(cacheKey, NULL_ATTRIBUTE);
            } else {
                String methodIdentification = ClassUtils.getQualifiedMethodName(method, targetClass);
                attr.setDescriptor(methodIdentification);
                this.attrCache.put(cacheKey, attr);
            }
            return attr;
        }
    }


    private JSpyWatcherAttribute computeJSpyWatcherAttribute(Method method, Class<?> targetClass) {

        JSpyWatcherAttribute attr = computeJSpyWatcherAttribute(method);
        if (null != attr) {
            return attr;
        }

        Class<?> userClass = ClassUtils.getUserClass(targetClass);
        Method specificMethod = ClassUtils.getMostSpecificMethod(method, userClass);
        specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);

        if (specificMethod != method) {
            attr = computeJSpyWatcherAttribute(method);
            if (null != attr) {
                return attr;
            }
        }
        return null;
    }


    private JSpyWatcherAttribute computeJSpyWatcherAttribute(Method method) {
        if (method.getAnnotations().length > 0) {
            return parseJSpyWatcherAttribute(method);
        }
        return null;
    }

    private JSpyWatcherAttribute parseJSpyWatcherAttribute(Method method) {
        AnnotationAttributes attributes =
                AnnotatedElementUtils.getMergedAnnotationAttributes(method, JSpyWatcher.class);
        if (null != attributes) {
            return parseJSpyWatcherAttribute(attributes);
        } else {
            return null;
        }
    }

    private JSpyWatcherAttribute parseJSpyWatcherAttribute(AnnotationAttributes attributes) {
        String name = attributes.getString("value");

        JSpyWatcherAttribute attr = new JSpyWatcherAttribute(name);
        attr.setQualifier(attributes.getString("value"));
        return attr;
    }


    private Object getCacheKey(Method method, Class<?> targetClass) {
        return new MethodClassKey(method, targetClass);
    }


}
