package org.chobit.jspy.interceptor;

import org.chobit.jspy.core.annotation.JSpyWatcher;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.MethodClassKey;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static org.chobit.jspy.core.utils.Strings.isBlank;

public class AnnotationWatcherAttributeSource implements WatcherAttributeSource {


    private final Map<Object, Optional<WatcherAttribute>> attrCache =
            new ConcurrentHashMap<>(1024);

    @Override
    public WatcherAttribute getWatcherAttribute(Method method, Class<?> targetClass) {

        if (method.getDeclaringClass() == Object.class) {
            return null;
        }

        Object cacheKey = getCacheKey(method, targetClass);
        Optional<WatcherAttribute> cached = attrCache.get(cacheKey);

        if (null == cached) {
            WatcherAttribute attr = computeWatcherAttribute(method, targetClass);
            if (null == attr) {
                attrCache.put(cacheKey, Optional.empty());
                return null;
            }

            String methodId = ClassUtils.getQualifiedMethodName(method, targetClass);
            attr.setMethodIdentity(methodId);

            attrCache.put(cacheKey, Optional.of(attr));
            return attr;
        } else {
            return cached.get();
        }
    }


    private WatcherAttribute computeWatcherAttribute(Method method, Class<?> targetClass) {

        WatcherAttribute attr = computeWatcherAttribute(method);
        if (null != attr) {
            return attr;
        }

        Class<?> userClass = ClassUtils.getUserClass(targetClass);
        Method specificMethod = ClassUtils.getMostSpecificMethod(method, userClass);
        specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);

        if (specificMethod != method) {
            attr = computeWatcherAttribute(method);
            return attr;
        }
        return null;
    }


    private WatcherAttribute computeWatcherAttribute(Method method) {
        if (method.getAnnotations().length > 0) {
            return parseWatcherAttribute(method);
        }
        return null;
    }

    private WatcherAttribute parseWatcherAttribute(Method method) {
        AnnotationAttributes attributes =
                AnnotatedElementUtils.getMergedAnnotationAttributes(method, JSpyWatcher.class);
        if (null != attributes) {
            return parseWatcherAttribute(attributes);
        } else {
            return null;
        }
    }

    private WatcherAttribute parseWatcherAttribute(AnnotationAttributes attributes) {
        String name = attributes.getString("name");
        if (isBlank(name)) {
            name = attributes.getString("value");
        }

        WatcherAttribute attr = new WatcherAttribute(name);
        attr.setMethodIdentity(attributes.getString("value"));
        return attr;
    }


    private Object getCacheKey(Method method, Class<?> targetClass) {
        return new MethodClassKey(method, targetClass);
    }


}
