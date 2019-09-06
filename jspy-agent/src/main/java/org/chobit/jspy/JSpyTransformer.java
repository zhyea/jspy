package org.chobit.jspy;


import org.chobit.jspy.asm.WatchedClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.concurrent.ConcurrentHashMap;


public class JSpyTransformer implements ClassFileTransformer {


    private ConcurrentHashMap<String, byte[]> map = new ConcurrentHashMap<>(128);


    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {


        if (map.containsKey("")) {
            return map.get("");
        }

        ClassReader cr = new ClassReader(classfileBuffer);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        WatchedClassAdapter tca = new WatchedClassAdapter(cw);
        cr.accept(tca, ClassReader.EXPAND_FRAMES);

        byte[] bytes = cw.toByteArray();

        map.putIfAbsent("", bytes);

        return bytes;
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
            /*if (null != clazz.getAnnotation(JSpyTimer.class)) {
                return true;
            }
            Method[] methods = clazz.getDeclaredMethods();
            for (Method m : methods) {
                if (null != m.getAnnotation(JSpyTimer.class)) {
                    return true;
                }
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
