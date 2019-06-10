package org.chobit.jspy.service.mapper;

import org.apache.ibatis.annotations.*;
import org.chobit.jspy.service.beans.MemoryUsage;

@Mapper
public interface MemoryPeakUsageMapper {


    @Insert({
            "insert into memory_usage (app_code, name, manager_names, type, host, init, used, committed, max, event_time)",
            "values",
            "(#{appCode}, #{name}, #{managerNames}, #{type}, #{host}, #{init}, #{used}, #{committed}, #{max}, #{eventTime})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(MemoryUsage memory);


    @Select("select * from memory_usage where app_code=#{appCode} and name=#{name} order by id desc limit 1")
    MemoryUsage getLatestByName(@Param("appCode") String appCode, @Param("name") String name);
}
