package com.zhyea.jspy.sample.asm;


import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ASM6;

public class PrintClassAdapter extends ClassVisitor {

    public PrintClassAdapter(ClassVisitor cv) {
        super(ASM6, cv);
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

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        super.visitAnnotation(desc, visible);
        System.out.println("-------class annotation: " + desc + " " + visible);
        return null;
    }


    @Override
    public FieldVisitor visitField(int access,
                                   String name,
                                   String desc,
                                   String signature,
                                   Object value) {
        System.out.println("--------filed access : " + access);
        System.out.println("--------field signature : " + signature);
        System.out.println("--------field value : " + value);
        System.out.println(" " + desc + " " + name);
        FieldVisitor fv =  cv.visitField(access, name, desc, signature, value);
        return new PrintFieldAdapter(fv);
    }


    public MethodVisitor visitMethod(int access,
                                     String name,
                                     String desc,
                                     String signature,
                                     String[] exceptions) {
        System.out.println("--------method access : " + access);
        System.out.println("--------method signature : " + signature);
        System.out.println(" " + name + desc);
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        if (mv != null) {
            return new PrintMethodAdapter(mv, access, name, desc);
        }
        return null;
    }


    public void visitEnd() {
        System.out.println("}");
    }
}