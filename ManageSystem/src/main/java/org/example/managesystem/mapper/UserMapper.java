package org.example.managesystem.mapper;

import org.apache.ibatis.annotations.*;
import org.example.managesystem.entity.User;

@Mapper
public interface UserMapper {
    //增（注册）
    @Insert("insert into user values (null,#{name},#{password})")
    int addUser(String name,String password);
    //删（注销）
    @Delete("delete from user where name=#{name} and password=#{password}")
    int deleteUser(User user);
    //改
    @Update("update user set name=#{name},password=#{password}")
    int updateUser(User user);
    //查
    @Select("select * from user where name=#{name} and password=#{password}")
    User getUserByName(User user);

}
