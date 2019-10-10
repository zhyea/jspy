package org.chobit.jspy.service;

import org.chobit.jspy.TestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MyServiceTest extends TestBase {

    @Autowired
    private MyService myService;

    @Test
    public void getStr(){
        System.out.println(myService.getStr());
        System.out.println(myService.getStr());
        System.out.println(myService.getStr());
        System.out.println(myService.getStr());
        System.out.println(myService.getStr());
        System.out.println(myService.getStr());
    }

}
