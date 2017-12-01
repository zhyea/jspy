package com.zhyea.jspy.sample;

import com.zhyea.jspy.annotations.JSpyTimer;
import jdk.internal.org.objectweb.asm.Type;

public class TestClient {


    public static void main(String[] args) {
        System.out.println(Type.getDescriptor(JSpyTimer.class));
        System.out.println(Type.getType(JSpyTimer.class));
        System.out.println(Type.getType(JSpyTimer.class).getDescriptor());
    }

}
