package org.chobit.jspy.agent;

import org.chobit.jspy.agent.asm.TimerClassAdapter;
import org.chobit.jspy.annotations.JSpyTimer;
import org.chobit.jspy.agent.constant.Config;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;
import java.util.concurrent.ConcurrentHashMap;

import static org.chobit.jspy.commons.tools.MD5.md5;

public class JSpyTransformer implements ClassFileTransformer {


    private static final String JSPY_PACKAGE = "org.chobit.jspy.*";

    private ConcurrentHashMap<String, byte[]> map = new ConcurrentHashMap<>(128);


    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {

        if (check(className)) {
            String md5Name = md5(className);

            if (map.containsKey(md5Name)) {
                return map.get(md5Name);
            }

            ClassReader cr = new ClassReader(classfileBuffer);
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);

            TimerClassAdapter tca = new TimerClassAdapter(cw);
            cr.accept(tca, ClassReader.EXPAND_FRAMES);

            byte[] bytes = cw.toByteArray();

            map.putIfAbsent(md5Name, bytes);

            return bytes;
        }

        return null;
    }


    /**
     * 检查类是否需要转换
     *
     * @param className 指定类的全限定类名
     * @return 指定的类是否需要转换
     */
    private boolean check(String className) {

        // 不转换jspy相关的类
        if (className.matches(JSPY_PACKAGE)) return false;
        // 转换配置文件中设置的类
        for (String pkg : Config.MONITOR_PACKAGES) {
            if (className.matches(pkg)) {
                if (!Config.MONITOR_ALL_METHODS) {
                    return checkJSpyTimerAnnotation(className);
                }
                return true;
            }
        }
        return false;
    }


    /**
     * 检查JSpyTimer注解是否在类中存在
     *
     * @param className 要检查的类的名称
     * @return 要检查的类是否存在JSpyTimer注解
     */
    private boolean checkJSpyTimerAnnotation(String className) {
        try {
            Class clazz = Class.forName(className);
            if (null != clazz.getAnnotation(JSpyTimer.class)) {
                return true;
            }
            Method[] methods = clazz.getDeclaredMethods();
            for (Method m : methods) {
                if (null != m.getAnnotation(JSpyTimer.class)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
