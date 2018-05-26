package com.jk.service;

import com.alibaba.fastjson.JSONObject;
import com.jk.pojo.User;

public interface UserService {

    String selectUserList(Integer page, Integer rows);

    void addUser(User user);

    void deleteUser(User user);

    User editQueryUser(User u);

}
