package org.chobit.jspy.service.mapper;

import org.apache.ibatis.annotations.*;
import org.chobit.jspy.constants.MetricTarget;
import org.chobit.jspy.service.entity.MetricTargetName;

import java.util.List;

@Mapper
public interface MetricTargetMapper {


    @Insert("insert into metric_target_name (app_code, target, `name`) values (#{appCode}, #{target}, #{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(MetricTargetName targetName);


    @Select("select `name` from metric_target_name where app_code=#{appCode} and target=#{target}")
    List<String> findNames(@Param("appCode") String appCode,
                           @Param("target") MetricTarget target);

}
