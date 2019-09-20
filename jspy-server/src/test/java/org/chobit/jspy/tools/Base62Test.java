package org.chobit.jspy.tools;


import org.junit.Test;

public class Base62Test {


    public void decode(){

    }


    @Test
    public void encode(){
        String s = Base62.encode(System.currentTimeMillis());
        System.out.println(s);
        String s1 = Base62.encode(System.nanoTime());
        System.out.println(s1);
    }

}
