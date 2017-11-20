package com.zhyea.jspy.sample;


import org.objectweb.asm.Type;

import static org.objectweb.asm.Opcodes.IMUL;

public class Test {


    //http://blog.csdn.net/wwwxxdddx/article/details/51878772
    //http://blog.csdn.net/wwwxxdddx/article/details/45064219


    public static void main(String[] args) throws Exception {

        System.out.println(Type.BOOLEAN_TYPE.getOpcode(IMUL));

        System.out.println(Test.class.getName().matches("abc.*"));

        //ASMifier.main(new String[]{Type.getInternalName(Account.class)});
    }


}
