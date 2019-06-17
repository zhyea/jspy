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

public class AnnotationWatcherAttributeSource implements WatcherAttributeSource {


    private final static WatcherAttribute NULL_ATTRIBUTE = new WatcherAttribute(null);

    private final Map<Object, WatcherAttribute> attrCache =
            new ConcurrentHashMap<>(1024);

    @Override
    public WatcherAttribute getWatcherAttribute(Method method, Class<?> targetClass) {

        if (method.getDeclaringClass() == Object.class) {
            return null;
        }

        Object cacheKey = getCacheKey(method, targetClass);
        WatcherAttribute cached = this.attrCache.get(cacheKey);

        if (null != cached) {
            return cached;
        } else {
            WatcherAttribute attr = computeJSpyWatcherAttribute(method, targetClass);
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


    private WatcherAttribute computeJSpyWatcherAttribute(Method method, Class<?> targetClass) {

        WatcherAttribute attr = computeJSpyWatcherAttribute(method);
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


    private WatcherAttribute computeJSpyWatcherAttribute(Method method) {
        if (method.getAnnotations().length > 0) {
            return parseJSpyWatcherAttribute(method);
        }
        return null;
    }

    private WatcherAttribute parseJSpyWatcherAttribute(Method method) {
        AnnotationAttributes attributes =
                AnnotatedElementUtils.getMergedAnnotationAttributes(method, JSpyWatcher.class);
        if (null != attributes) {
            return parseJSpyWatcherAttribute(attributes);
        } else {
            return null;
        }
    }

    private WatcherAttribute parseJSpyWatcherAttribute(AnnotationAttributes attributes) {
        String name = attributes.getString("value");

        WatcherAttribute attr = new WatcherAttribute(name);
        attr.setQualifier(attributes.getString("value"));
        return attr;
    }


    private Object getCacheKey(Method method, Class<?> targetClass) {
        return new MethodClassKey(method, targetClass);
    }


}
