package org.chobit.jspy.service.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.chobit.jspy.model.QueryParam;

import static org.chobit.jspy.core.utils.Strings.isNotBlank;

public class MetricQueryProvider {


    public String queryWithQueryParam(@Param("table") String tableName,
                                      @Param("appCode") String appCode,
                                      @Param("p") QueryParam param,
                                      @Param("targetColumn") String targetColumn,
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
                WHERE("app_code=#{appCode}");
                if (isNotBlank(param.getTarget()) && isNotBlank(targetColumn)) {
                    WHERE(targetColumn + "=#{p.target}");
                }
                if (null != param.getStartTime()) {
                    WHERE("event_time>#{p.startTime}");
                }
                if (null != param.getEndTime()) {
                    WHERE("event_time<#{p.endTime}");
                }
                if (param.isUsePeak()) {
                    WHERE("is_peak=#{p.isPeak}");
                }
            }
        }.toString();
    }

}
