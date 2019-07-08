package org.chobit.jspy.utils;


import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class ShortCodeTest {


    @Test
    public void gen() {

        Set<String> s = new HashSet<>(3000);

        for (int i = 0; i < 3000; i++) {
            String code = ShortCode.gen();
            s.add(code);
            System.out.println(code);
        }

        System.out.println(s.size());

    }


}
