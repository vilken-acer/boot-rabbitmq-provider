package com.jk.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Mapper;


import com.jk.pojo.User;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select count(1) from t_boot_user")
    long selectUserTotal();

    @Select("select * from t_boot_user limit #{page},#{rows}")
    @Results({
            @Result(property="userId",column="user_id"),
            @Result(property="userName",column="user_name"),
            @Result(property="userSex",column="user_sex"),
            @Result(property="userAge",column="user_age"),
            @Result(property="userBirthday",column="user_birthday")
    })
    List<User> selectUserList(@Param(value="page")Integer page, @Param(value="rows")Integer rows);

    @Update("update t_boot_user set user_name=#{userName},user_sex=#{userSex},user_age=#{userAge},user_birthday=#{userBirthday} where user_id=#{userId}")
    void updateUser(User user);

    @Insert("insert into t_boot_user(user_id,user_name,user_sex,user_age,user_birthday) values(#{userId},#{userName},#{userSex},#{userAge},#{userBirthday})")
    void addUser(User user);

    @Delete("delete from t_boot_user where user_id=#{userId}")
    void deleteUser(User user);

    @Select("select * from t_boot_user where user_id=#{userId}")
    @Results({
            @Result(property="userId",column="user_id"),
            @Result(property="userName",column="user_name"),
            @Result(property="userSex",column="user_sex"),
            @Result(property="userAge",column="user_age"),
            @Result(property="userBirthday",column="user_birthday")
    })
    User editQueryUser(User u);
}
