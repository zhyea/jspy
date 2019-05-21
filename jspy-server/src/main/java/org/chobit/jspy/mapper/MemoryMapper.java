package org.chobit.jspy.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.chobit.jspy.beans.Memory;

import java.util.Date;
import java.util.List;

@Mapper
public interface MemoryMapper {


    @Insert({"insert into memory (app_id, type, init, used, committed, max, event_time)",
            "values",
            "(#{appId}, #{type}, #{init}, #{used}, #{committed}, #{max}, #{eventTime})"})
    int insert(Memory memory);


    @Select("select * from memory")
    List<Memory> findAll();


    @Select("select * from memory where event_time>#{eventTime}")
    List<Memory> findByEventTime(@Param("eventTime") Date eventTime);
}
