package com.zhyea.jspy.sample.asm;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.FieldVisitor;

import static org.objectweb.asm.Opcodes.ASM6;

public class PrintFieldAdapter extends FieldVisitor {

    public PrintFieldAdapter(FieldVisitor fv) {
        super(ASM6, fv);
    }


    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        System.out.println("-------field annotation desc  : " + desc);
        System.out.println("-------field annotation visible : " + desc);
        return null;
    }

    public void visitAttribute(Attribute attr) {
        System.out.println("-------field attr : " + attr);
    }

}
