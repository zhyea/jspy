package org.chobit.jspy.service.mapper;


import org.apache.ibatis.annotations.*;
import org.chobit.jspy.core.annotation.JSpyWatcher;
import org.chobit.jspy.service.entity.HistogramEntity;
import org.chobit.jspy.tools.LowerCaseKeyMap;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface HistogramMapper {


    @Select("select distinct name from histogram where type=#{type}")
    List<String> findNames(@Param("type") int type);


    @Select("select name, sum(count) as sum from histogram where app_code=#{appCode} and type=#{type} group by name")
    List<LowerCaseKeyMap> findNamesAndCount(@Param("appCode") String appCode,
                                            @Param("type") int type);


    @Insert({
            "insertHistograms into histogram(app_code, ip, `type`, `name`, count, failed_count, std_dev, min, max, mean, sum,",
            "percentile999, percentile98, percentile95, percentile90, percentile75, median, event_time)",
            "values",
            "(#{appCode}, #{ip}, #{type}, #{name}, #{count}, #{failedCount}, #{stdDev}, #{min}, #{max}, #{mean}, #{sum},",
            " #{percentile999}, #{percentile98}, #{percentile95}, #{percentile90}, #{percentile75}, #{median}, #{eventTime})",
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(HistogramEntity histogram);


    @JSpyWatcher("JSpyWatcher注解方法信息-Mapper.insertHistograms")
    @Insert({
            "<script>",
            "insertHistograms into histogram(app_code, ip, `type`, `name`, count, failed_count, std_dev, min, max, mean, sum,",
            "percentile999, percentile98, percentile95, percentile90, percentile75, median, event_time)",
            "values",
            "<foreach collection='histograms' item='item' separator=','>",
            "(#{item.appCode}, #{item.ip}, #{item.type}, #{item.name}, #{item.count}, #{item.failedCount},",
            " #{item.stdDev}, #{item.min}, #{item.max}, #{item.mean}, #{item.sum},",
            " #{item.percentile999}, #{item.percentile98}, #{item.percentile95}, #{item.percentile90}, #{item.percentile75}, #{item.median}, #{item.eventTime})",
            "</foreach>",
            "</script>"
    })
    int batchInsert(@Param("histograms") Iterable<HistogramEntity> histograms);


    @Select({"select * from histogram where ",
            "app_code=#{appCode} and `type`=#{type} and `name`=#{name}",
            "and event_time>=#{start} and event_time<#{end}"})
    List<LowerCaseKeyMap> findForChart(@Param("appCode") String appCode,
                                       @Param("type") int type,
                                       @Param("name") String name,
                                       @Param("start") Date start,
                                       @Param("end") Date end);


    @Select({"select sum(count) as all, sum(failed_count) as failed from histogram",
            "where app_code=#{appCode} and name=#{name} and event_time>=#{time}"})
    Map<String, Long> countByTime(@Param("appCode") String appCode,
                                  @Param("name") String name,
                                  @Param("time") Date time);
}
