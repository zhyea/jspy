package org.chobit.jspy.utils;


import org.junit.Test;

public class ShortCoderTest {


    @Test
    public void gen() {

        for (int i = 0; i < 3000; i++) {
            System.out.println(ShortCode.gen());
        }

    }


}
