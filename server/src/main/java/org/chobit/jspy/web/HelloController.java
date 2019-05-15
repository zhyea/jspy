package org.chobit.jspy.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/m")
public class HelloController {


    @GetMapping("/hello")
    public String hello(){
        return "Thanks";
    }

}
