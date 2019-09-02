package org.chobit.jspy.service.mapper;


import org.apache.ibatis.annotations.*;
import org.chobit.jspy.service.entity.App;

import java.util.List;

@Mapper
public interface AppMapper {

    @Insert("insert into app (app_code, app_name) values (#{appCode}, #{appName})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(App app);


    @Update("update app set app_name=#{appName} where id=#{id}")
    boolean update(@Param("appName") String appName, @Param("id") int id);


    @Select("select * from app where deleted=0")
    List<App> findAll();


    @Select("select * from app where deleted=0 and id=#{id}")
    App get(@Param("id") int id);


    @Select("select * from app where deleted=0 and app_code=#{appCode}")
    App getByAppCode(@Param("appCode") String appCode);


    @Select("select * from app where deleted=0 and app_name=#{appName} order by id desc limit 1")
    App getByAppName(@Param("appName") String appName);


    @Update("update app set deleted=1 where id=#{id}")
    boolean logicDelete(@Param("id") int id);
}
