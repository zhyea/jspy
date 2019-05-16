package org.chobit.jspy.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/m")
public class HelloController {


    @GetMapping("/hello")
    public String hello(){
        return "Thanks";
    }

}
