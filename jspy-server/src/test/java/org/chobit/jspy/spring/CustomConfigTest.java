package org.chobit.jspy.spring;

import org.chobit.jspy.TestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomConfigTest extends TestBase {

    @Autowired
    private CustomConfig config;

    @Test
    public void valueOf(){
        System.out.println(config.getDataReserveDates());
    }
}
