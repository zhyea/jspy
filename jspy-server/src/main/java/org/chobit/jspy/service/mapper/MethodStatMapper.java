package org.chobit.jspy.service.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.chobit.jspy.core.annotation.JSpyWatcher;
import org.chobit.jspy.service.beans.MethodStat;

@Mapper
public interface MethodStatMapper {



    @JSpyWatcher("JSpyWatcher注解方法信息-Mapper.insert")
    @Insert({
            "<script>",
            "insert into method_stat(app_code, ip, method_id, std_dev, min, max, mean, percentile999, percentile98, percentile95, percentile90, percentile75, median)",
            "values",
            "<foreach collection='gcStats' item='item' separator=','>",
            "(#{item.appCode}, #{item.ip}, #{item.methodId}, #{item.stdDev}, #{item.min}, #{item.max}, #{item.mean},",
            " #{item.percentile999}, #{item.percentile98}, #{item.percentile95}, #{item.percentile90}, #{item.percentile75}, #{item.median})",
            "</foreach>",
            "</script>"
    })
    int batchInsert(Iterable<MethodStat> stats);

}
