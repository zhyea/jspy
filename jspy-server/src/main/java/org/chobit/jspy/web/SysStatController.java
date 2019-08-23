package org.chobit.jspy.web;


import org.chobit.jspy.core.model.Item;
import org.chobit.jspy.service.SysStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sys")
public class SysStatController {

    @Autowired
    private SysStatService service;

    @PostMapping("/receive")
    public void receive(@RequestHeader("appCode") String appCode,
                        @RequestHeader("ip") String ip,
                        @RequestBody List<Item> stats) {
        service.insert(appCode, ip, stats);
    }
    
}
