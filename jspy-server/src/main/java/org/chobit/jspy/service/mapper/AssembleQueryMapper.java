package org.chobit.jspy.service.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.chobit.jspy.model.ChartParam;
import org.chobit.jspy.model.page.Page;
import org.chobit.jspy.tools.LowerCaseKeyMap;

import java.util.Date;
import java.util.List;

/**
 * 组装查询
 */
@Mapper
public interface AssembleQueryMapper {


    @SelectProvider(type = AssembleQueryProvider.class, method = "findForChart")
    List<LowerCaseKeyMap> findForChart(@Param("table") String tableName,
                                       @Param("appCode") String appCode,
                                       @Param("p") ChartParam param,
                                       @Param("targetColumn") String targetColumn,
                                       @Param("columns") String... columns
    );


    @SelectProvider(type = AssembleQueryProvider.class, method = "queryInPage")
    List<LowerCaseKeyMap> findInPage(@Param("table") String tableName,
                                     @Param("appCode") String appCode,
                                     @Param("p") Page page,
                                     @Param("searchColumn") Iterable<String> searchColumns,
                                     @Param("columns") String... resultColumns);


    @SelectProvider(type = AssembleQueryProvider.class, method = "countInPage")
    long countInPage(@Param("table") String tableName,
                     @Param("appCode") String appCode,
                     @Param("p") Page page,
                     @Param("searchColumn") Iterable<String> searchColumns
    );


    @Delete("delete from ${table} where ${dateColumn} <= #{date}")
    int deleteByDate(@Param("table") String tableName,
                     @Param("dateColumn") String dateColumn,
                     @Param("date") Date date);


    @Delete({"<script>",
            "delete from ${table} where id in",
            "<foreach collection='ids' item='item' separator=','>",
            "#{item}",
            "</foreach>",
            "</script>"})
    int deleteByIds(@Param("table") String tableName,
                    @Param("ids") Iterable<Integer> ids);

}
