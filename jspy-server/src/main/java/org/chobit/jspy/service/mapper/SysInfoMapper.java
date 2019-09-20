package org.chobit.jspy.service.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

@Mapper
public interface SysInfoMapper {


    @Insert({"insert into sys_info (app_code, ip, `type`, detail, event_time) ",
            "values ",
            "(#{appCode}, #{ip}, #{type}, #{detail}, #{eventTime})"})
    int insert(@Param("appCode") String appCode,
               @Param("ip") String ip,
               @Param("type") int type,
               @Param("detail") String detail,
               @Param("eventTime") Date eventTime);


    @Select("select detail from sys_info where app_code=#{appCode} and `type`=#{type} order by event_time desc limit 1")
    String getLatest(@Param("appCode") String appCode,
                     @Param("type") int type);
}
