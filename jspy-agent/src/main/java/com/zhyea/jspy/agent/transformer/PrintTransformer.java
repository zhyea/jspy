package com.zhyea.jspy.agent.transformer;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class PrintTransformer implements ClassFileTransformer {


    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {

        System.out.println("----- name: " + className);
        System.out.println("----- redefine: " + classBeingRedefined);
        System.out.println("----- domain: " + protectionDomain);
        System.out.println("----- buffer: " + classfileBuffer);
        System.out.println("---------------------------------------------------");
        return null;
    }

}
