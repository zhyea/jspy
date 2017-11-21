package com.zhyea.jspy.sample.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Type;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * 类轨迹跟踪。用于辅助类与方法的转换
 */
public class ClassTracer {

    /**
     * 类轨迹跟踪方法。默认将跟踪结果输出到控制台
     *
     * @param clazz 要跟踪分析的类
     */
    public static void trace(Class clazz) {
        trace(clazz, System.out);
    }


    /**
     * 类轨迹跟踪方法。
     * @param clazz 要跟踪分析的类
     * @param out
     */
    public static void trace(Class clazz, OutputStream out) {
        try {
            PrintWriter printWriter = new PrintWriter(out);
            TraceClassVisitor tcv = new TraceClassVisitor(printWriter);
            ClassReader cr = new ClassReader(Type.getInternalName(clazz));
            cr.accept(tcv, 0);
        } catch (Exception e) {
            throw new RuntimeException("trace class error.");
        }
    }

}
