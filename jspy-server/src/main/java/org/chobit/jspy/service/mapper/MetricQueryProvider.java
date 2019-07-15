package org.chobit.jspy.service.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.chobit.jspy.model.QueryParam;

public class MetricQueryProvider {


    public String queryWithQueryParam(@Param("table") String tableName,
                                      @Param("p") QueryParam param,
                                      @Param("conditionColumn") String conditionColumn,
                                      @Param("columns") String... resultColumns) {
        return new SQL() {
            {
                if (null == resultColumns || resultColumns.length == 0) {
                    SELECT("*");
                } else {
                    SELECT(resultColumns);
                }
                FROM(tableName);
                WHERE("deleted=0");
                if (null != param.getCondition()) {
                    WHERE(conditionColumn + "=#{p.condition}");
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
