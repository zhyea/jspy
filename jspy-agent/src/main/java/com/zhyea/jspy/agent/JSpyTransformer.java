package com.zhyea.jspy.agent;

import com.zhyea.jspy.agent.asm.TimerClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.concurrent.ConcurrentHashMap;

import static com.zhyea.jspy.agent.constant.Config.MONITOR_PACKAGES;
import static com.zhyea.jspy.commons.tools.MD5.md5;

public class JSpyTransformer implements ClassFileTransformer {

    private String option;

    private Instrumentation instrumentation;

    private ConcurrentHashMap<String, byte[]> map = new ConcurrentHashMap<>(128);

    public JSpyTransformer(String option, Instrumentation ins) {
        this.option = option;
        this.instrumentation = ins;
    }


    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
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


    private boolean check(String className) {
        for (String pkg : MONITOR_PACKAGES) {
            if (className.matches(pkg)) return true;
        }
        return false;
    }
}
