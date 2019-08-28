package org.chobit.jspy.web;

import org.chobit.jspy.core.annotation.JSpyWatcher;
import org.chobit.jspy.core.model.Item;
import org.chobit.jspy.service.*;
import org.chobit.jspy.service.entity.App;
import org.chobit.jspy.service.entity.MethodEntity;
import org.chobit.jspy.utils.Args;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class FrontEndController {

    @Autowired
    private GcService gcService;
    @Autowired
    private AppService appService;
    @Autowired
    private MemoryService memoryService;
    @Autowired
    private MethodService methodService;
    @Autowired
    private SysInfoService sysService;


    /**
     * 跳转到类信息页
     */
    @GetMapping("/classes")
    public String classLoading(@SessionAttribute("appCode") String appCode,
                               ModelMap model) {
        List<Item> items = sysService.getLatestRuntimeInfo(appCode);
        model.addAttribute("runtime", items);

        return "classes";
    }


    /**
     * 跳转到线程数据页
     */
    @GetMapping("/thread")
    public String thread() {
        return "thread";
    }


    /**
     * 跳转到方法数据页
     */
    @GetMapping("/method-detail/{id}")
    public String methodDetail(@PathVariable("id") int id,
                               HttpSession session) {
        MethodEntity entity = methodService.get(id);
        session.setAttribute("methodName", entity.getName());
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
    @JSpyWatcher("跳转到内存信息页")
    @GetMapping("/memory")
    public String memory(@SessionAttribute("appCode") String appCode,
                         ModelMap model) {
        List<String> memTypeNames = memoryService.getMemTypeNames();
        Set<String> heapPoolNames = memoryService.getHeapPoolNames(appCode);
        Set<String> nonHeapPoolNames = memoryService.getNonHeapPoolNames(appCode);

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
     * 跳转到应用信息首页
     */
    @GetMapping("/app/home/{appCode}")
    public String appHome(@PathVariable("appCode") String appCode,
                          ModelMap model, HttpSession session) {

        App app = appService.getByAppCode(appCode);
        Args.checkNotNull(app, "无效应用码");

        session.setAttribute("appCode", appCode);
        session.setAttribute("appName", app.getAppName());

        List<Item> details = sysService.getLatestSysInfo(appCode);
        model.addAttribute("sysInfo", details);

        return "app-home";
    }

}
