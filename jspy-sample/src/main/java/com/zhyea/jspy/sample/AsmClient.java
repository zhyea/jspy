package com.zhyea.jspy.sample.asm;

import com.zhyea.jspy.sample.model.Account;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Type;

import java.io.IOException;

public class AsmClient {

    public static void main(String[] args) throws IOException {
        ClassPrinter cp = new ClassPrinter();
        ClassReader cr = new ClassReader(Type.getInternalName(Account.class));
        cr.accept(cp, 0);
    }


}
