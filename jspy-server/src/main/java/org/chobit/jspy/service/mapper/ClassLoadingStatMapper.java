package org.chobit.jspy.service.mapper;

import org.apache.ibatis.annotations.*;
import org.chobit.jspy.core.annotation.JSpyWatcher;
import org.chobit.jspy.service.entity.ClassLoadingStat;

import java.util.Date;

@Mapper
public interface ClassLoadingStatMapper {


    @JSpyWatcher("加载的类的信息-Mapper.insert")
    @Insert({"insert into class_loading_stat (app_code, ip, total_loaded, current_loaded, unloaded, event_time)",
            "values",
            "(#{appCode}, #{ip}, #{totalLoaded}, #{currentLoaded}, #{unloaded}, #{eventTime})"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(ClassLoadingStat stat);


    @Select("select * from class_loading_stat where app_code=#{appCode} and event_time>#{time} order by id desc limit 1")
    ClassLoadingStat getLatest(@Param("appCode") String appCode, @Param("time") Date time);
}
