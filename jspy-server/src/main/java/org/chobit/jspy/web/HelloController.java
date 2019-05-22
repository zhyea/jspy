package org.chobit.jspy.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String welcome(ModelMap model) {
        model.put("message", "Hello Thymeleaf!");
        return "hello";
    }

    @GetMapping("/")
    public String index(ModelMap model) {
        model.put("message", "Hello Thymeleaf!");
        return "index";
    }

    @GetMapping("/home")
    public String home(ModelMap model) {
        return "home";
    }

}
