package org.chobit.jspy.web;


import org.chobit.jspy.model.*;
import org.chobit.jspy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/message")
public class MessageReceiveController {

    @Autowired
    private ClassLoadingService classLoadingService;
    @Autowired
    private CpuService cpuService;
    @Autowired
    private GcService gcService;
    @Autowired
    private MemoryService memoryService;
    @Autowired
    private MethodService methodService;
    @Autowired
    private SysInfoService sysInfoService;
    @Autowired
    private ThreadService threadService;

    @PostMapping("/receive")
    public boolean receive(@RequestHeader("appCode") String appCode,
                           @RequestHeader("ip") String ip,
                           @RequestBody MessagePack pack) {
        if (null == pack) {
            return false;
        }

        insertClassLoadings(appCode, ip, pack.getClasses());
        insertCpu(appCode, ip, pack.getCpuUsage());
        insertGc(appCode, ip, pack.getGc());
        insertMemory(appCode, ip, pack.getMemories());
        insertMethods(appCode, ip, pack.getMethods());

        sysInfoService.insertRuntimeInfo(appCode, ip, pack.getRuntime());
        sysInfoService.insertSysInfo(appCode, ip, pack.getSysInfo());

        insertThreads(appCode, ip, pack.getThreads());

        return true;
    }

    private void insertClassLoadings(String appCode, String ip, Iterable<ClassLoadingCount> classes) {
        if (null != classes) {
            classes.forEach(e -> classLoadingService.insert(appCode, ip, e));
        }
    }

    private void insertCpu(String appCode, String ip, Map<Long, Double> cpuUsages) {
        if (null != cpuUsages) {
            cpuUsages.forEach((t, u) -> cpuService.insert(appCode, ip, new BigDecimal(u), t));
        }
    }

    private void insertGc(String appCode, String ip, List<GcOverview> gc) {
        if (null != gc) {
            gc.forEach(e -> gcService.insert(appCode, ip, e));
        }
    }

    private void insertMemory(String appCode, String ip, List<MemoryOverview> memories) {
        if (null != memories) {
            memories.forEach(e -> memoryService.insert(appCode, ip, e));
        }
    }

    private void insertMethods(String appCode, String ip, List<MethodHistogram> methods) {
        if (null != methods) {
            methods.forEach(e -> methodService.insertHistograms(appCode, ip, e));
        }
    }

    private void insertThreads(String appCode, String ip, List<ThreadOverview> threads) {
        if (null != threads) {
            threads.forEach(e -> threadService.insert(appCode, ip, e));
        }
    }

}
