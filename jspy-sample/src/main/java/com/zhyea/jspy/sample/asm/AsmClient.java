package com.zhyea.jspy.sample.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Type;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;

public class AsmClient {

    public static void main(String[] args) throws IOException {
        ClassPrinter cp = new ClassPrinter();
        PrintWriter printWriter = new PrintWriter(System.out);
        TraceClassVisitor tcv = new TraceClassVisitor(printWriter);
        ClassReader cr = new ClassReader(Type.getInternalName(Account.class));
        cr.accept(tcv, 0);
    }


}
