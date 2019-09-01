package org.chobit.jspy.web;


import org.chobit.jspy.core.model.Item;
import org.chobit.jspy.service.SysInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sys")
public class SysInfoController {

    @Autowired
    private SysInfoService service;

    @PostMapping("/receive")
    public void receive(@RequestHeader("appCode") String appCode,
                        @RequestHeader("ip") String ip,
                        @RequestBody List<Item> stats) {
        service.insertSysInfo(appCode, ip, stats);
    }

    @PostMapping("/runtime/receive")
    public void receiveRuntime(@RequestHeader("appCode") String appCode,
                               @RequestHeader("ip") String ip,
                               @RequestBody List<Item> stats) {
        service.insertRuntimeInfo(appCode, ip, stats);
    }

}
