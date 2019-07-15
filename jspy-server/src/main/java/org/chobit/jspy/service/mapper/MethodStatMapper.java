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
            "(#{appCode}, #{ip}, #{methodId}, #{stdDev}, #{min}, #{max}, #{mean},",
            " #{percentile999}, #{percentile98}, #{percentile95}, #{percentile90}, #{percentile75}, #{median})",
            "</foreach>",
            "</script>"
    })
    int batchInsert(Iterable<MethodStat> stats);

}
