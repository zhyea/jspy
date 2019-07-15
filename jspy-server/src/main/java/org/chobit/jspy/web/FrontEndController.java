package org.chobit.jspy.web;

import org.chobit.jspy.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("app")
public class FrontEndController {


    @Autowired
    private AppService appService;


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

    @GetMapping("/app-home/{appCode}")
    public String appHome(@PathVariable("appCode") String appCode, ModelMap model, HttpSession session) {
        model.addAttribute("app", appService.getByAppCode(appCode));
        session.setAttribute("appCode", appCode);
        return "app-home";
    }

}
