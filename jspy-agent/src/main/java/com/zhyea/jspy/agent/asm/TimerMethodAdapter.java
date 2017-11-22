package com.zhyea.jspy.agent.asm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class TimerMethodAdapter extends AdviceAdapter {

    private String owner;

    private boolean isInterface;


    public TimerMethodAdapter(final MethodVisitor methodVisitor,
                              final int access,
                              final String name,
                              final String desc,
                              final boolean isInterface) {
        super(ASM6, methodVisitor, access, name, desc);
        this.owner = name;
        this.isInterface = isInterface;
    }

    @Override
    protected void onMethodEnter() {
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System",
                "currentTimeMillis", "()J", isInterface);
        mv.visitInsn(LSTORE);
        mv.visitFieldInsn(PUTSTATIC, owner, "timer", "J");
    }

    @Override
    protected void onMethodExit(int opcode) {
        mv.visitFieldInsn(GETSTATIC, owner, "timer", "J");
        mv.visitMethodInsn(INVOKESTATIC, owner,
                "currentTimeMillis", "()J", isInterface);
        mv.visitInsn(LADD);
        mv.visitFieldInsn(PUTSTATIC, owner, "timer", "J");
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        super.visitMaxs(maxStack + 4, maxLocals);
    }
}
