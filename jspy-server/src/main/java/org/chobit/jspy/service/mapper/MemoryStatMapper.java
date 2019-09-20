package org.chobit.jspy.service.mapper;

import org.apache.ibatis.annotations.*;
import org.chobit.jspy.core.annotation.JSpyWatcher;
import org.chobit.jspy.service.entity.MemoryStat;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
     * 根据内存区域名称获取最近的内存用量
     */
    @Select("select * from memory_stat where app_code=#{appCode} and name=#{name} and event_time>#{time} and is_peak=#{isPeak} order by id desc limit 1")
    MemoryStat getLatestByName(@Param("appCode") String appCode,
                               @Param("name") String name,
                               @Param("time") Date time,
                               @Param("isPeak") int isPeak);


    /**
     * 获取堆 内存池名称
     */
    @Select("select distinct(`name`) from memory_stat where app_code=#{appCode} and type='HEAP' and manager_names<>'null'")
    Set<String> findHeapPoolNames(@Param("appCode") String appCode);

    /**
     * 获取非堆内存池名称
     */
    @Select("select distinct(`name`) from memory_stat where app_code=#{appCode} and type='NON_HEAP' and manager_names<>'null'")
    Set<String> findNonHeapPoolNames(@Param("appCode") String appCode);

    /**
     * 按时间删除记录
     */
    @Delete("delete from memory_stat where event_time < #{time}")
    int deleteByTime(@Param("time") Date time);

    /**
     * 删除记录
     */
    @Delete("delete from memory_stat where id=#{id}")
    boolean delete(@Param("id") int id);

    /**
     * 按时间获取记录
     */
    @Select("select * from memory_stat where event_time>#{time} and is_peak=#{isPeak}")
    List<MemoryStat> findByTime(@Param("time") Date time, @Param("isPeak") int isPeak);
}
