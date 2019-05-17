package org.chobit.jspy.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.chobit.jspy.beans.User;

import java.util.List;

@Mapper
public interface UserMapper {


    @Select("select * from user")
    List<User> findAll();


}
