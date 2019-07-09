package org.chobit.jspy.service.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.chobit.jspy.service.beans.ThreadStat;

@Mapper
public interface ThreadStatMapper {


    @Insert({"insert into thread_stat (app_code, ip, current, peak, total_started, daemon)",
    "values",
    "(#{appCode}, #{ip}, #{current}, #{peak}, #{totalStarted}, #{daemon})"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(ThreadStat stat);


}
