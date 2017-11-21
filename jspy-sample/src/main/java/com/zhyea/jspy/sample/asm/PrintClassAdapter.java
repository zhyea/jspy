package com.zhyea.jspy.sample.asm;


import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ASM6;

public class ClassPrinter extends ClassVisitor {

    public ClassPrinter() {
        super(ASM6);
    }

    @Override
    public void visit(int version,
                      int access,
                      String name,
                      String signature,
                      String superName,
                      String[] interfaces) {
        System.out.println(name + " extends " + superName + " {");
    }


    public FieldVisitor visitField(int access,
                                   String name,
                                   String desc,
                                   String signature,
                                   Object value) {
        System.out.println(" " + desc + " " + name);
        return null;
    }


    public MethodVisitor visitMethod(int access,
                                     String name,
                                     String desc,
                                     String signature,
                                     String[] exceptions) {
        System.out.println(" " + name + desc);
        return null;
    }


    public void visitEnd() {
        System.out.println("}");
    }
}