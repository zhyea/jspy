package org.chobit.jspy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.chobit.jspy.model.QueryParam;
import org.chobit.jspy.tools.LinkedLowerCaseKeyMap;

import java.util.List;

@Mapper
public interface MetricQueryMapper {


    @SelectProvider(type = MetricQueryProvider.class, method = "queryWithQueryParam")
    List<LinkedLowerCaseKeyMap> findByParams(@Param("table") String tableName,
                                             @Param("p") QueryParam param,
                                             @Param("columns") String... columns);

}
