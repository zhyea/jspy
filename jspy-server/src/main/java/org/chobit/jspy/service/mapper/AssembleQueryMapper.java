package org.chobit.jspy.service.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.chobit.jspy.model.QueryParam;
import org.chobit.jspy.model.page.Page;
import org.chobit.jspy.tools.LowerCaseKeyMap;

import java.util.List;

/**
 * 组装查询
 */
@Mapper
public interface AssembleQueryMapper {


    @SelectProvider(type = AssembleQueryProvider.class, method = "queryWithQueryParam")
    List<LowerCaseKeyMap> findWithQueryParam(@Param("table") String tableName,
                                             @Param("appCode") String appCode,
                                             @Param("p") QueryParam param,
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

}
