package org.chobit.jspy.service.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.chobit.jspy.model.QueryParam;

public class MetricQueryProvider {


    public String queryWithQueryParam(@Param("table") String tableName,
                                      @Param("p") QueryParam param,
                                      @Param("columns") String... columns) {
        return new SQL() {
            {
                if (null == columns || columns.length == 0) {
                    SELECT("*");
                } else {
                    SELECT(columns);
                }
                FROM(tableName);
                WHERE("deleted=0");
                if (null != param.getName()) {
                    WHERE("name=#{p.name}");
                }
                if (null != param.getStartTime()) {
                    WHERE("event_time>=#{p.startTime}");
                }
                if (null != param.getEndTime()) {
                    WHERE("event_time<=#{p.endTime}");
                }
            }
        }.toString();
    }

}
