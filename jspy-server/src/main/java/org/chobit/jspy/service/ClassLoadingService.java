package org.chobit.jspy.service;

import org.chobit.jspy.core.model.Item;
import org.chobit.jspy.model.ChartParam;
import org.chobit.jspy.model.ClassLoadingCount;
import org.chobit.jspy.service.common.AssembleQueryService;
import org.chobit.jspy.service.entity.ClassLoadingStat;
import org.chobit.jspy.service.mapper.ClassLoadingStatMapper;
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
    private AssembleQueryService aqService;

    private static final String TABLE_NAME = "class_loading_stat";


    public int insert(String appCode, String ip, ClassLoadingCount gauge) {
        if (isClose(appCode, gauge)) {
            return -1;
        }

        return mapper.insert(new ClassLoadingStat(appCode, ip, gauge));
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
     * 查询类加载报表数据
     */
    public List<LowerCaseKeyMap> findForChart(String appCode, ChartParam param) {
        return aqService.findForChart(TABLE_NAME, appCode, param,
                "total_loaded", "current_loaded", "unloaded", "event_time");
    }


    /**
     * 获取最新的数据
     */
    public Item getLatest(String appCode) {
        Date time = new Date(SysTime.millis() - TimeUnit.MINUTES.toMillis(15));
        ClassLoadingStat stat = mapper.getLatest(appCode, time);
        if (null == stat) {
            return null;
        }
        Item item = new Item("类加载信息");
        item.add("已装载当前类", stat.getCurrentLoaded());
        item.add("已装载类总数", stat.getTotalLoaded());
        item.add("已卸载类总数", stat.getUnloaded());
        return item;
    }


    /**
     * 删除记录
     */
    public int delete() {
        return aqService.delete(TABLE_NAME);
    }

}
