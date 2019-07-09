package org.chobit.jspy.service.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.chobit.jspy.service.beans.ClassLoadingStat;

@Mapper
public interface ClassLoadingStatMapper {


    @Insert({"insert into thread_stat (app_code, ip, total_loaded, current_loaded, unloaded)",
            "values",
            "(#{appCode}, #{ip}, #{totalLoaded}, #{currentLoaded}, #{unloaded})"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(ClassLoadingStat stat);


}
