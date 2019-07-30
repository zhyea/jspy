package org.chobit.jspy.web;


import org.chobit.jspy.model.Histogram;
import org.chobit.jspy.service.MethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/method")
public class MethodController {


    @Autowired
    private MethodService methodService;


    @PostMapping("/receive")
    public boolean receive(@RequestHeader("appCode") String appCode,
                           @RequestHeader("ip") String ip,
                           @RequestBody List<Histogram> histograms) {
        return methodService.insert(appCode, ip, histograms);
    }


}
