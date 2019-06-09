package org.chobit.jspy.service.mapper;

import org.apache.ibatis.annotations.*;
import org.chobit.jspy.service.beans.MemoryUsage;

import java.util.List;

@Mapper
public interface MemoryUsageMapper {


    @Insert({
            "insert into memory_usage (app_code, pool_name, manager_names, type, init, used, committed, max, event_time)",
            "values",
            "(#{appCode}, #{poolName}, #{managerNames}, #{type}, #{init}, #{used}, #{committed}, #{max}, #{eventTime})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(MemoryUsage memory);


    @Insert({
            "<script>",
            "insert into memory_usage (app_code, pool_name, manager_names, type, init, used, committed, max, event_time)",
            "values",
            "<foreach collection='memories' item='item' separator=','>",
            "(#{item.appCode}, #{item.poolName}, #{item.managerNames}, #{item.type}, #{item.init}, #{item.used}, #{item.committed}, #{item.max}, #{item.eventTime})",
            "</foreach>",
            "</script>"
    })
    int batchInsert(@Param("memories") List<MemoryUsage> memories);



    @Select("select distinct pool_name from memory_usage")
    List<String> findMemoryPoolNames();
}
