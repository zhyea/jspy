package org.chobit.jspy.web;

import org.chobit.jspy.service.AppService;
import org.chobit.jspy.service.GcService;
import org.chobit.jspy.service.MemoryService;
import org.chobit.jspy.service.beans.App;
import org.chobit.jspy.utils.Args;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class FrontEndController {

    @Autowired
    private GcService gcService;
    @Autowired
    private AppService appService;
    @Autowired
    private MemoryService memoryService;

    /**
     * 跳转到方法数据页
     */
    @PostMapping("/method-detail")
    public String methodDetail(@RequestBody String methodName, HttpSession session) {
        session.setAttribute("methodName", methodName);
        return "method-detail";
    }


    /**
     * 跳转到方法列表页
     */
    @GetMapping("/method-list")
    public String allMethods() {
        return "method-list";
    }

    /**
     * 跳转到内存数据页
     */
    @GetMapping("/memory")
    public String memory(@SessionAttribute("appCode") String appCode, ModelMap model) {
        List<String> memTypeNames = memoryService.findMemTypeNames();
        List<String> heapPoolNames = memoryService.findHeapPoolNames(appCode);
        List<String> nonHeapPoolNames = memoryService.findNonHeapPoolNames(appCode);

        model.addAttribute("memTypeNames", memTypeNames);
        model.addAttribute("heapPoolNames", heapPoolNames);
        model.addAttribute("nonHeapPoolNames", nonHeapPoolNames);

        return "memory";
    }

    /**
     * 跳转到GC数据页
     */
    @GetMapping("/gc")
    public String gc(ModelMap model) {
        List<String> names = gcService.findHistogramNames();

        model.addAttribute("names", names);
        return "gc";
    }

    /**
     * 跳转到首页
     */
    @RequestMapping("/")
    public String index(ModelMap model) {
        model.put("isIndex", true);
        return "index";
    }

    /**
     * 应用信息首页
     */
    @GetMapping("/app/home/{appCode}")
    public String appHome(@PathVariable("appCode") String appCode, HttpSession session) {
        App app = appService.getByAppCode(appCode);
        Args.checkNotNull(app, "无效应用码");

        session.setAttribute("appCode", appCode);
        session.setAttribute("appName", app.getAppName());
        return "app-home";
    }

}
