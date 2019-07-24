package org.chobit.jspy.service.mapper;

import org.apache.ibatis.annotations.*;
import org.chobit.jspy.core.annotation.JSpyWatcher;
import org.chobit.jspy.service.beans.MemoryStat;

import java.util.Date;
import java.util.List;

@Mapper
public interface MemoryStatMapper {


    /**
     * 写入数据
     */
    @JSpyWatcher("内存信息-Mapper.insert")
    @Insert({
            "insert into memory_stat (app_code, ip, name, manager_names, type, init, used, committed, max, event_time, is_peak)",
            "values",
            "(#{appCode}, #{ip}, #{name}, #{managerNames}, #{type}, #{init}, #{used}, #{committed}, #{max}, #{eventTime}, #{isPeak})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(MemoryStat memory);


    /**
     * 根据内存区域名称获取最近的内存使用峰值
     */
    @Select("select * from memory_stat where app_code=#{appCode} and name=#{name} and is_peak=1 and event_time>#{time} order by id desc limit 1")
    MemoryStat getLatestPeakByName(@Param("appCode") String appCode, @Param("name") String name, @Param("time") Date time);


    /**
     * 根据内存区域名称获取最近的内存用量
     */
    @Select("select * from memory_stat where app_code=#{appCode} and name=#{name} and is_peak=0 and event_time>#{time} order by id desc limit 1")
    MemoryStat getLatestByName(@Param("appCode") String appCode, @Param("name") String name, @Param("time") Date time);

    /**
     * 获取内存类型名称
     */
    @Select("select distinct(`name`) from memory_stat where app_code=#{appCode} and manager_names='null'")
    List<String> findMemTypeNames(@Param("appCode") String appCode);

    /**
     * 获取堆 内存池名称
     */
    @Select("select distinct(`name`) from memory_stat where app_code=#{appCode} and type='HEAP' and manager_names<>'null'")
    List<String> findHeapPoolNames(@Param("appCode") String appCode);

    /**
     * 获取非堆内存池名称
     */
    @Select("select distinct(`name`) from memory_stat where app_code=#{appCode} and type='NON_HEAP' and manager_names<>'null'")
    List<String> findNonHeapPoolNames(@Param("appCode") String appCode);


}
