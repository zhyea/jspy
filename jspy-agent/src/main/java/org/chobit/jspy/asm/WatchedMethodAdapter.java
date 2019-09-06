package org.chobit.jspy.asm;

import org.chobit.jspy.core.annotation.JSpyWatcher;
import org.chobit.jspy.core.support.JSpyWatcherCollector;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

public class WatchedMethodAdapter extends AdviceAdapter {

    private String owner;

    private String desc;

    private boolean isInterface;

    private int start;

    private int end;

    private boolean isMonitor;


    private static final String systemOwner =
            System.class.getName().replaceAll("\\.", "/");

    private static final String watcherAnnotationDesc = Type.getDescriptor(JSpyWatcher.class);

    private static final String watcherOwner = JSpyWatcherCollector.class.getName().replaceAll("\\.", "/");

    public WatchedMethodAdapter(final MethodVisitor methodVisitor,
                                final int access,
                                final String name,
                                final String desc,
                                final String owner,
                                final boolean isInterface) {
        super(ASM6, methodVisitor, access, name, desc);
        this.owner = owner + "." + name;
        this.desc = desc;
        this.isInterface = isInterface;
        this.isMonitor = !name.equals("<init>") && isMonitor;
    }


    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        if (!isMonitor && visible && null != desc && desc.equals(watcherAnnotationDesc)) {
            isMonitor = true;
        }
        return super.visitAnnotation(desc, visible);
    }


    @Override
    protected void onMethodEnter() {
        if (isMonitor) {
            mv.visitMethodInsn(INVOKESTATIC, systemOwner, "currentTimeMillis", "()J", isInterface);
            start = newLocal(Type.LONG_TYPE);
            mv.visitVarInsn(LSTORE, start);
        }
    }


    @Override
    protected void onMethodExit(int opcode) {
        if (isMonitor) {
            mv.visitMethodInsn(INVOKESTATIC, systemOwner, "currentTimeMillis", "()J", isInterface);
            end = newLocal(Type.LONG_TYPE);
            mv.visitVarInsn(LSTORE, end);

            mv.visitLdcInsn(owner);
            mv.visitLdcInsn(desc);

            mv.visitVarInsn(LLOAD, end);
            mv.visitVarInsn(LLOAD, start);
            mv.visitInsn(LSUB);

            mv.visitMethodInsn(INVOKESTATIC, watcherOwner,
                    "add", "(Ljava/lang/String;Ljava/lang/String;J)V", isInterface);
        }
    }


    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        super.visitMaxs(maxStack + 4, maxLocals);
    }
}
