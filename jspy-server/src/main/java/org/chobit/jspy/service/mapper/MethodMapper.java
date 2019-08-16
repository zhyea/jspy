package org.chobit.jspy.service.mapper;


import org.apache.ibatis.annotations.*;
import org.chobit.jspy.service.entity.MethodEntity;

import java.util.List;

@Mapper
public interface MethodMapper {


    @Insert({"insertHistograms into method(app_code, ip, `name`, recent_count, recent_failed)",
            "values",
            "(#{appCode}, #{ip}, #{name}, #{recentCount}, #{recentFailed})"})
    int insert(MethodEntity method);


    @Update({"update method set recent_count=#{recentCount}, recent_failed=#{recentFailed}",
            "where id=#{id}"})
    boolean update(MethodEntity method);


    @Select("select * from method where app_code=#{appCode} and `name`=#{name}")
    MethodEntity findByName(@Param("appCode") String appCode,
                            @Param("name") String name);


    @Select("select * from method where id=#{id}")
    MethodEntity get(@Param("id") int id);


    @Select("select * from method where app_code=#{appCode}")
    List<MethodEntity> findByAppCode(@Param("appCode") String appCode);
}
