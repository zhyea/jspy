package org.chobit.jspy.service.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.chobit.jspy.model.QueryParam;
import org.chobit.jspy.tools.LowerCaseKeyMap;

import java.util.List;

@Mapper
public interface MetricQueryMapper {


    @SelectProvider(type = MetricQueryProvider.class, method = "queryWithQueryParam")
    List<LowerCaseKeyMap> findWithQueryParam(@Param("table") String tableName,
                                             @Param("appCode") String appCode,
                                             @Param("p") QueryParam param,
                                             @Param("targetColumn") String targetColumn,
                                             @Param("columns") String... columns
    );

}
