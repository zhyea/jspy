package org.chobit.jspy.web;

import org.chobit.jspy.core.model.GcRecord;
import org.chobit.jspy.service.GcService;
import org.chobit.jspy.utils.Args;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gc")
public class GcStatController {

    @Autowired
    private GcService gcService;


    @PostMapping("/receive")
    public boolean receive(@RequestHeader("appCode") String appCode,
                           @RequestHeader("ip") String ip,
                           @RequestBody List<GcRecord> gcRecords) {
        Args.checkNotBlank(appCode, "appCode(应用码)不能为空");
        return gcService.insert(appCode, ip, gcRecords);
    }


}
