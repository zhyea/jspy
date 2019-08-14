package org.chobit.jspy.service.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.chobit.jspy.model.QueryParam;
import org.chobit.jspy.model.page.Page;

import static org.chobit.jspy.core.utils.Strings.isBlank;
import static org.chobit.jspy.core.utils.Strings.isNotBlank;
import static org.chobit.jspy.utils.Collections2.isNotAllBlank;

public class AssembleQueryProvider {


    /**
     * 组装查询参数查询语句，通常用于报表
     */
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
                    WHERE("event_time>=#{p.startTime}");
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


    /**
     * 组装分页查询语句
     */
    public String queryInPage(@Param("table") String tableName,
                              @Param("appCode") String appCode,
                              @Param("p") Page page,
                              @Param("searchColumn") Iterable<String> searchColumns,
                              @Param("columns") String... resultColumns) {

        String sql = queryInPageCondition(false, tableName, appCode, page, searchColumns, resultColumns);

        if (null != page.getOrder()) {
            sql += " " + page.getOrder();
        }

        if (0 < page.getLimit()) {
            sql += " limit ";
            if (0 <= page.getOffset()) {
                sql += page.getOffset() + ", ";
            }
            sql += page.getLimit();
        }


        return sql;
    }


    /**
     * 组装分页查询统计语句
     */
    public String countInPage(@Param("table") String tableName,
                              @Param("appCode") String appCode,
                              @Param("p") Page page,
                              @Param("searchColumn") Iterable<String> searchColumns) {
        return queryInPageCondition(true, tableName, appCode, page, searchColumns);
    }


    /**
     * 分页查询基础条件
     */
    private String queryInPageCondition(boolean isCount,
                                        @Param("table") String tableName,
                                        @Param("appCode") String appCode,
                                        @Param("p") Page page,
                                        @Param("searchColumn") Iterable<String> searchColumns,
                                        @Param("columns") String... resultColumns) {
        return new SQL() {
            {
                if (isCount) {
                    SELECT("count(id)");
                } else if (null == resultColumns || resultColumns.length == 0) {
                    SELECT("*");
                } else {
                    SELECT(resultColumns);
                }
                FROM(tableName);
                WHERE("app_code=#{appCode}");

                if (isNotBlank(page.getSearch()) && isNotAllBlank(searchColumns)) {
                    StringBuilder builder = new StringBuilder();

                    page.setSearch("%" + page.getSearch() + "%");

                    int i = 0;
                    for (String s : searchColumns) {
                        if (isBlank(s)) {
                            continue;
                        }
                        if (i++ > 0) {
                            builder.append(" or ");
                        }
                        builder.append(s + " like '" + page.getSearch() + "'");
                    }

                    AND();
                    WHERE(builder.toString());
                }


                if (!isCount && isNotBlank(page.getSort())) {
                    ORDER_BY(page.getSort());
                }
            }
        }.toString();
    }
}
