package org.chobit.jspy.web;

import org.chobit.jspy.service.AppService;
import org.chobit.jspy.service.beans.App;
import org.chobit.jspy.utils.Args;
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
        model.put("isIndex", true);
        return "index";
    }

    @GetMapping("/app-home/{appCode}")
    public String appHome(@PathVariable("appCode") String appCode, HttpSession session) {
        App app = appService.getByAppCode(appCode);
        Args.checkNotNull(app, "无效应用码");

        session.setAttribute("appCode", appCode);
        session.setAttribute("appName", app.getAppName());
        return "app-home";
    }

}
