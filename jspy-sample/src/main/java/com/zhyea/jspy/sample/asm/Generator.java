package com.zhyea.jspy.sample.asm;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;

/**
 * https://www.ibm.com/developerworks/cn/java/j-lo-asm30/
 */

public class Generator {

    public static void main(String[] args) throws Exception {

        ClassReader cr = new ClassReader(Account.class.getName());
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassAdapter classAdapter = new AddSecurityCheckClassAdapter(cw);
        cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
        byte[] data = cw.toByteArray();
        File file = new File("/Account.class");
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(data);
        fout.close();
    }

}
