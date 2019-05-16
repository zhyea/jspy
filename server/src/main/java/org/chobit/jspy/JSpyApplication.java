package org.chobit.jspy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
@SpringBootConfiguration
public class JSpyApplication {


    public static void main(String[] args) {
        SpringApplication.run(JSpyApplication.class, args);
    }


}
