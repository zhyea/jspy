package org.chobit.jspy.service.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.chobit.jspy.core.annotation.JSpyWatcher;
import org.chobit.jspy.service.beans.HistogramEntity;

@Mapper
public interface HistogramMapper {


    @Insert({
            "insert into histogram(app_code, ip, `type`, `name`, count, std_dev, min, max, mean, ",
            "percentile999, percentile98, percentile95, percentile90, percentile75, median, event_time)",
            "values",
            "(#{appCode}, #{ip}, #{type}, #{name}, #{count}, #{stdDev}, #{min}, #{max}, #{mean},",
            " #{percentile999}, #{percentile98}, #{percentile95}, #{percentile90}, #{percentile75}, #{median}, #{eventTime})",
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(HistogramEntity histogram);


    @JSpyWatcher("JSpyWatcher注解方法信息-Mapper.insert")
    @Insert({
            "<script>",
            "insert into histogram(app_code, ip, `type`, `name`, count, std_dev, min, max, mean, ",
            "percentile999, percentile98, percentile95, percentile90, percentile75, median, event_time)",
            "values",
            "<foreach collection='histograms' item='item' separator=','>",
            "(#{item.appCode}, #{item.ip}, #{item.type}, #{item.name}, #{item.count}, #{item.stdDev}, #{item.min}, #{item.max}, #{item.mean},",
            " #{item.percentile999}, #{item.percentile98}, #{item.percentile95}, #{item.percentile90}, #{item.percentile75}, #{item.median}, #{item.eventTime})",
            "</foreach>",
            "</script>"
    })
    int batchInsert(@Param("histograms") Iterable<HistogramEntity> histograms);
}
