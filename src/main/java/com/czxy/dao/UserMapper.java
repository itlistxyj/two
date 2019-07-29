package com.czxy.dao;

import com.czxy.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
@org.apache.ibatis.annotations.Mapper
public interface UserMapper  extends Mapper<User> {
    @Select("select * from user where name=#{name} and password =#{password}")
    public User findUserByNameAndPassword(@Param("name") String name, @Param("password") String password);
}
