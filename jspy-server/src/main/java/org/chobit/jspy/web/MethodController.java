package org.chobit.jspy.web;


import org.chobit.jspy.model.MethodHistogram;
import org.chobit.jspy.service.MethodService;
import org.chobit.jspy.utils.Args;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/method")
public class MethodController {


    @Autowired
    private MethodService methodService;


    @PostMapping("/receive")
    public boolean receive(@RequestHeader("appCode") String appCode,
                           @RequestHeader("ip") String ip,
                           @RequestBody List<MethodHistogram> histograms) {
        Args.checkNotBlank(appCode, "appCode(应用码)不能为空");
        return methodService.insert(appCode, ip, histograms);
    }


}
