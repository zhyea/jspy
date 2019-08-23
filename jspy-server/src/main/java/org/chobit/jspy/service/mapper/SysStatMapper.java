package org.chobit.jspy.service.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysStatMapper {


    @Insert("insert into sys_stat (app_code, ip, detail) values (#{appCode}, #{ip}, #{detail})")
    int insert(@Param("appCode") String appCode,
               @Param("ip") String ip,
               @Param("detail") String detail);


    @Select("select detail from sys_stat where app_code=#{appCode} order by event_time desc limit 1")
    String getLatest(@Param("appCode") String appCode);
}
