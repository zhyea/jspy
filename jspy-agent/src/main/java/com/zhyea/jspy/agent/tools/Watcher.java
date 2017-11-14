package com.zhyea.jspy.agent.tools;


import org.objectweb.asm.ClassReader;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;

public class Watcher implements ClassFileTransformer {

    public static void premain(String options, Instrumentation ins) {

    }

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {


        System.out.println();
        return null;
    }


    public byte[] tansform(String className) throws IOException, ClassNotFoundException {

        ClassReader classReader = new ClassReader(className);
        Class<?> clazz = Class.forName(className);
        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            System.out.println(m);
        }
        return null;
    }
}