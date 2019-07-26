package org.chobit.jspy.service.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.chobit.jspy.core.annotation.JSpyWatcher;
import org.chobit.jspy.service.beans.MethodStat;

@Mapper
public interface MethodStatMapper {


    @JSpyWatcher("JSpyWatcher注解方法信息-Mapper.insert")
    @Insert({
            "<script>",
            "insert into method_stat(app_code, ip, method_id, count, std_dev, min, max, mean, percentile999, percentile98, percentile95, percentile90, percentile75, median, event_time)",
            "values",
            "<foreach collection='mStats' item='item' separator=','>",
            "(#{item.appCode}, #{item.ip}, #{item.methodId}, #{item.count}, #{item.stdDev}, #{item.min}, #{item.max}, #{item.mean},",
            " #{item.percentile999}, #{item.percentile98}, #{item.percentile95}, #{item.percentile90}, #{item.percentile75}, #{item.median}, #{item.eventTime})",
            "</foreach>",
            "</script>"
    })
    int batchInsert(@Param("mStats") Iterable<MethodStat> stats);

}
