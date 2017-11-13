package com.zhyea.jspy.sample;

import com.zhyea.jspy.sample.asm.Account;
import org.objectweb.asm.Type;
import org.objectweb.asm.util.ASMifier;

public class Test {




    //http://blog.csdn.net/wwwxxdddx/article/details/51878772
    //http://blog.csdn.net/wwwxxdddx/article/details/45064219


    public static void main(String[] args) throws Exception {

        ASMifier.main(new String[]{Type.getInternalName(Account.class)});
    }


}
