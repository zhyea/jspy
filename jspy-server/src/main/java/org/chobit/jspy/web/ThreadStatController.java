package org.chobit.jspy.web;

import org.chobit.jspy.model.ThreadGauge;
import org.chobit.jspy.service.ThreadService;
import org.chobit.jspy.utils.Args;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/thread")
public class ThreadStatController {

    @Autowired
    private ThreadService threadService;


    @PostMapping("/receive")
    public int receive(@RequestHeader("appCode") String appCode,
                       @RequestHeader("ip") String ip,
                       @RequestBody ThreadGauge threadGauge) {
        Args.checkNotBlank(appCode, "appCode(应用码)不能为空");
        return threadService.insert(appCode, ip, threadGauge);
    }


}
