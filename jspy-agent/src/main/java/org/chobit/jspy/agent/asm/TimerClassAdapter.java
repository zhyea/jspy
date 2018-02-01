package org.chobit.jspy.agent.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.chobit.jspy.agent.constant.Config.MONITOR_ALL_METHODS;
import static org.objectweb.asm.Opcodes.ACC_INTERFACE;
import static org.objectweb.asm.Opcodes.ASM6;

public class TimerClassAdapter extends ClassVisitor {

    private boolean isInterface;
    private String owner;
    private boolean isMonitor = MONITOR_ALL_METHODS;

    public TimerClassAdapter(ClassVisitor classVisitor) {
        super(ASM6, classVisitor);
    }

    @Override
    public void visit(int version,
                      int access,
                      String name,
                      String signature,
                      String superName,
                      String[] interfaces) {
        cv.visit(version, access, name, signature, superName, interfaces);
        isInterface = (access & ACC_INTERFACE) != 0;
        owner = name;
    }


    @Override
    public MethodVisitor visitMethod(int access,
                                     String name,
                                     String desc,
                                     String signature,
                                     String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        if (!isInterface && mv != null) {
            mv = new TimerMethodAdapter(mv, access, name, desc, owner, false, isMonitor);
        }
        return mv;
    }
}
