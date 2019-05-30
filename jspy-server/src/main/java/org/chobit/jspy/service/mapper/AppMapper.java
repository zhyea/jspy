package org.chobit.jspy.service.mapper;


import org.apache.ibatis.annotations.*;
import org.chobit.jspy.service.beans.App;

import java.util.List;

public interface AppMapper {

    @Insert("insert into app (app_code, app_name) values (#{appCode}, #{appName})")
    @Options(useGeneratedKeys = true)
    int insert(App app);


    @Update("update app set app_name=#{appName} where id=#{id}")
    boolean update(@Param("appName") String appName, @Param("id") int id);


    @Select("select * from app where deleted=0")
    List<App> findAll();


    @Select("select * from app where id=#{id}")
    App get(@Param("id") int id);


    @Select("select * from app where app_code=#{appCode}")
    App getByAppCode(@Param("appCode") String appCode);

    @Update("update app set deleted=1 where id=#{id}")
    boolean logicDelete(@Param("id") int id);

}
