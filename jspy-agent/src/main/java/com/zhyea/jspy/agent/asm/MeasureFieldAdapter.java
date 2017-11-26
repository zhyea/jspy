package com.zhyea.jspy.agent.asm;

import org.objectweb.asm.FieldVisitor;

import static org.objectweb.asm.Opcodes.ASM6;

public class MeasureFieldAdapter extends FieldVisitor {

    public MeasureFieldAdapter(FieldVisitor fv) {
        super(ASM6, fv);
    }
}
