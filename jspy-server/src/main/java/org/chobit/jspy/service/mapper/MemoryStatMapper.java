package org.chobit.jspy.service.mapper;

import org.apache.ibatis.annotations.*;
import org.chobit.jspy.core.annotation.JSpyWatcher;
import org.chobit.jspy.service.beans.MemoryStat;

import java.util.List;

@Mapper
public interface MemoryStatMapper {


    /**
     * 写入数据
     */
    @JSpyWatcher("内存信息-Mapper.insert")
    @Insert({
            "insert into memory_stat (app_code, ip, name, manager_names, type, init, used, committed, max, event_time)",
            "values",
            "(#{appCode}, #{ip}, #{name}, #{managerNames}, #{type}, #{init}, #{used}, #{committed}, #{max}, #{eventTime})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(MemoryStat memory);


    /**
     * 查询内存区域名称
     */
    @Select("select distinct name from memory_stat where app_code=#{appCode}")
    List<String> findMemoryNames(@Param("appCode") String appCode);


    @Select("select * from memory_stat where app_code=#{appCode} and name=#{name} and is_peak=1 order by id desc limit 1")
    MemoryStat getLatestPeakByName(@Param("appCode") String appCode, @Param("name") String name);
}
