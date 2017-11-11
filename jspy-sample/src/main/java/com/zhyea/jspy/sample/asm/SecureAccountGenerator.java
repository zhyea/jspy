package com.zhyea.jspy.sample.asm;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class SecureAccountGenerator {

    private static AccountGeneratorClassLoader classLoader = new AccountGeneratorClassLoader();

    private static Class secureAccountClass;

    public Account generateSecureAccount() throws ClassFormatError, InstantiationException, IllegalAccessException, IOException {
        if (null == secureAccountClass) {
            ClassReader cr = new ClassReader(Account.class.getName());
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            ClassAdapter classAdapter = new AddSecurityCheckClassAdapter(cw);
            cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
            byte[] data = cw.toByteArray();
            File f = new File("/Account.class");
            RandomAccessFile raf = new RandomAccessFile(f, "rw");
            raf.write(data, 0, data.length);
            raf.close();

            secureAccountClass = classLoader.defineClassFromClassFile(Account.class.getName(), data);
        }
        return (Account) secureAccountClass.newInstance();
    }


    private static class AccountGeneratorClassLoader extends ClassLoader {

        public Class defineClassFromClassFile(String className, byte[] classFile) throws ClassFormatError {
            return defineClass(className + "$EnhancedByASM", classFile, 0, classFile.length);
        }

    }
}
