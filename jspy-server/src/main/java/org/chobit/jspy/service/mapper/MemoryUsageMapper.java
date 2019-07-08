package org.chobit.jspy.service.mapper;

import org.apache.ibatis.annotations.*;
import org.chobit.jspy.service.beans.MemoryUsage;

import java.util.List;

@Mapper
public interface MemoryUsageMapper {


    /**
     * 写入数据
     */
    @Insert({
            "insert into memory_usage (app_code, name, manager_names, type, host, init, used, committed, max, event_time)",
            "values",
            "(#{appCode}, #{name}, #{managerNames}, #{type}, #{host}, #{init}, #{used}, #{committed}, #{max}, #{eventTime})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(MemoryUsage memory);


    /**
     * 批量写入数据
     */
    @Insert({
            "<script>",
            "insert into memory_usage (app_code, name, manager_names, type, host, init, used, committed, max, event_time)",
            "values",
            "<foreach collection='memories' item='item' separator=','>",
            "(#{item.appCode}, #{item.name}, #{item.managerNames}, #{item.type}, #{item.host}, #{item.init}, #{item.used}, #{item.committed}, #{item.max}, #{item.eventTime})",
            "</foreach>",
            "</script>"
    })
    int batchInsert(@Param("memories") List<MemoryUsage> memories);


    /**
     * 查询内存区域名称
     */
    @Select("select distinct name from memory_usage where app_code=#{appCode}")
    List<String> findMemoryNames(@Param("appCode") String appCode);





    @Select("select * from memory_usage where app_code=#{appCode} and name=#{name} and is_peak=1 order by id desc limit 1")
    MemoryUsage getLatestPeakByName(@Param("appCode") String appCode, @Param("name") String name);
}
