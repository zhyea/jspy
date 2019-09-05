package org.chobit.jspy.service.common;

import org.chobit.jspy.model.ChartParam;
import org.chobit.jspy.model.page.Page;
import org.chobit.jspy.model.page.PageResult;
import org.chobit.jspy.service.mapper.AssembleQueryMapper;
import org.chobit.jspy.spring.CustomConfig;
import org.chobit.jspy.tools.LowerCaseKeyMap;
import org.chobit.jspy.utils.SysTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 管理各种组装查询能力
 */
@Service
public class AssembleQueryService {

    @Autowired
    private CustomConfig config;
    @Autowired
    private AssembleQueryMapper queryMapper;
    @Autowired
    private DataShrinkService shrinkService;

    /**
     * 删除记录
     */
    public int delete(String tableName, String dateColumn) {
        long time = SysTime.millis() - TimeUnit.DAYS.toMillis(config.getDataReserveDates());
        Date date = new Date(time);
        return queryMapper.delete(tableName, dateColumn, date);
    }

    /**
     * 删除记录
     */
    public int delete(String tableName) {
        return delete(tableName, "event_time");
    }


    /**
     * 查询报表数据
     */
    public List<LowerCaseKeyMap> findForChart(String tableName,
                                              String targetColumn,
                                              String appCode,
                                              ChartParam param,
                                              String... columns) {
        List<LowerCaseKeyMap> r = queryMapper.findForChart(tableName, appCode, param, targetColumn, columns);
        if (param.getEndTime().getTime() - param.getStartTime().getTime() > TimeUnit.HOURS.toMillis(3)) {
            return shrinkService.shrink(r);
        }
        return r;
    }


    /**
     * 查询报表数据
     */
    public List<LowerCaseKeyMap> findForChart(String tableName,
                                              String appCode,
                                              ChartParam param,
                                              String... columns) {

        return findForChart(tableName, null, appCode, param, columns);
    }

    /**
     * 查询列表数据
     *
     * @param tableName     表名
     * @param searchColumns 要搜索的字段名
     * @param appCode       appCode
     * @param page          分页对象
     * @param resultColumns 查询结果字段
     * @return 查询结果
     */
    public PageResult<LowerCaseKeyMap> findInPage(String tableName,
                                                  List<String> searchColumns,
                                                  String appCode,
                                                  Page page,
                                                  String... resultColumns) {
        List<LowerCaseKeyMap> rows = queryMapper.findInPage(tableName, appCode, page, searchColumns, resultColumns);
        long total = queryMapper.countInPage(tableName, appCode, page, searchColumns);
        return new PageResult<>(total, rows);
    }


}
