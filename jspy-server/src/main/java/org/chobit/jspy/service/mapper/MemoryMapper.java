package org.chobit.jspy.service.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.chobit.jspy.service.beans.Memory;

import java.util.List;

@Mapper
public interface MemoryMapper {


    @Insert({
            "insert into memory (app_id, type, init, used, committed, max, event_time)",
            "values",
            "(#{appId}, #{type}, #{init}, #{used}, #{committed}, #{max}, #{eventTime})"
    })
    int insert(Memory memory);


    @Insert({
            "<script>",
            "insert into memory (app_id, type, init, used, committed, max, event_time)",
            "values",
            "<foreach collection='memories' item='item' separator=','>",
            "(#{item.appId}, #{item.type}, #{item.init}, #{item.used}, #{item.committed}, #{item.max}, #{item.eventTime})",
            "</foreach>",
            "</script>"
    })
    int batchInsert(@Param("memories") List<Memory> memories);

}
