package org.chobit.jspy.service.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;

@Mapper
public interface CpuMapper {


    @Insert({"insert into cpu_usage (app_code, ip, usage, event_time)",
            "values",
            "(#{appCode}, #{ip}, #{usage}, #{eventTime})"})
    int insert(@Param("appCode") String appCode,
               @Param("ip") String ip,
               @Param("usage") BigDecimal usage,
               @Param("eventTime") Date eventTime);


}
