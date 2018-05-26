package com.jk.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jk.mapper.UserMapper;
import com.jk.pojo.User;
import com.jk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

   @Autowired
   private UserMapper userMapper;

    @Override
    public String selectUserList(Integer page, Integer rows) {
        long total = userMapper.selectUserTotal();
        List<User> list = userMapper.selectUserList(page,rows);


        Map<String,Object> jsons = new HashMap<>();
        jsons.put("total", total);
        jsons.put("rows", list);

        return JSON.toJSONStringWithDateFormat(jsons,"yyyy-MM-dd", SerializerFeature.DisableCircularReferenceDetect);
    }

    @Override
    public void addUser(User user) {
        if (user.getUserId().length()>0) {
            userMapper.updateUser(user);
        }else{
            user.setUserId(new Date().getTime()+"");
            userMapper.addUser(user);
        }
    }

    @Override
    public void deleteUser(User user) {
        userMapper.deleteUser(user);
    }

    @Override
    public User editQueryUser(User u) {
        User user = userMapper.editQueryUser(u);
        return user;
    }
}

