package org.chobit.jspy.web;

import org.chobit.jspy.model.GcOverview;
import org.chobit.jspy.service.GcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gc")
public class GcController {

    @Autowired
    private GcService gcService;


    @PostMapping("/receive")
    public boolean receive(@RequestHeader("appCode") String appCode,
                           @RequestHeader("ip") String ip,
                           @RequestBody GcOverview overview) {
        return gcService.insert(appCode, ip, overview);
    }


}
