package com.zhyea.jspy.sample.asm;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class PrintMethodAdapter extends AdviceAdapter {

    private String owner;

    public PrintMethodAdapter(final MethodVisitor methodVisitor,
                              final int access,
                              final String name,
                              final String desc) {
        super(ASM6, methodVisitor, access, name, desc);
        this.owner = name;
    }


    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        super.visitAnnotation(desc, visible);
        System.out.println("------method annotation : " + desc + " " + visible);
        return null;
    }

    @Override
    protected void onMethodEnter() {
        System.out.println("on method " + owner + " enter.");
    }

    @Override
    protected void onMethodExit(int opcode) {
        System.out.println("on method " + owner + " exit.");
        System.out.println(opcode);
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        super.visitMaxs(maxStack + 4, maxLocals);
    }
}
