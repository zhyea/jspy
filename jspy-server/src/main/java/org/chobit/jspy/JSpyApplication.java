package org.chobit.jspy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JSpyApplication {


    public static void main(String[] args) {
        SpringApplication.run(JSpyApplication.class, args);
    }


}
