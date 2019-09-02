package org.chobit.jspy.service;

import org.chobit.jspy.core.model.Item;
import org.chobit.jspy.model.ClassLoadingCount;
import org.chobit.jspy.model.QueryParam;
import org.chobit.jspy.service.entity.ClassLoadingStat;
import org.chobit.jspy.service.mapper.ClassLoadingStatMapper;
import org.chobit.jspy.service.mapper.AssembleQueryMapper;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.chobit.jspy.utils.SysTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ClassLoadingService {

    @Autowired
    private ClassLoadingStatMapper mapper;
    @Autowired
    private AssembleQueryMapper aqMapper;

    public int insert(String appCode, String ip, ClassLoadingCount gauge) {
        if (isClose(appCode, gauge)) {
            return -1;
        }

        ClassLoadingStat stat = new ClassLoadingStat();
        stat.setAppCode(appCode);
        stat.setIp(ip);
        stat.setTotalLoaded(gauge.getTotalLoaded());
        stat.setCurrentLoaded(gauge.getCurrentLoaded());
        stat.setUnloaded(gauge.getUnloaded());
        stat.setEventTime(new Date(gauge.getEventTime() > 0 ? gauge.getEventTime() : SysTime.millis()));
        return mapper.insert(stat);
    }


    private boolean isClose(String appCode, ClassLoadingCount gauge) {
        Date time = new Date(SysTime.millis() - TimeUnit.MINUTES.toMillis(15));
        ClassLoadingStat latest = mapper.getLatest(appCode, time);

        if (null == latest) {
            return false;
        }
        if (latest.getCurrentLoaded() != gauge.getCurrentLoaded()) {
            return false;
        }
        if (latest.getTotalLoaded() != gauge.getTotalLoaded()) {
            return false;
        }
        if (latest.getUnloaded() != gauge.getUnloaded()) {
            return false;
        }
        return true;
    }

    /**
     * 查询类加载数据
     */
    public List<LowerCaseKeyMap> findForChart(String appCode, QueryParam param) {
        return aqMapper.findWithQueryParam("class_loading_stat",
                appCode,
                param,
                null,
                "total_loaded", "current_loaded", "unloaded", "event_time");
    }


    /**
     * 获取最新的数据
     */
    public Item getLatest(String appCode) {
        Date time = new Date(SysTime.millis() - TimeUnit.MINUTES.toMillis(15));
        ClassLoadingStat stat = mapper.getLatest(appCode, time);
        Item item = new Item("类加载信息");
        item.add("已装载当前类", stat.getCurrentLoaded());
        item.add("已装载类总数", stat.getTotalLoaded());
        item.add("已卸载类总数", stat.getUnloaded());
        return item;
    }

}
