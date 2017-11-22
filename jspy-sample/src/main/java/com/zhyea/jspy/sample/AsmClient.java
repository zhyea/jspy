package com.zhyea.jspy.sample;

import com.zhyea.jspy.sample.asm.PrintClassAdapter;
import com.zhyea.jspy.sample.model.TestModel;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;

import java.io.IOException;

/**
 * 测试类，用于验证方法参数信息
 */
public class AsmClient {

    public static void main(String[] args) throws IOException {
        ClassReader cr = new ClassReader(Type.getInternalName(TestModel.class));
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        PrintClassAdapter cp = new PrintClassAdapter(cw);
        cr.accept(cp, 0);
    }


}
