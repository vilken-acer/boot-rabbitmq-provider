package com.jk.controller;

import com.jk.mq.Send;
import com.jk.pojo.User;
import com.jk.service.UserService;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value="user")
public class UserController {

    @Autowired
    private Send send;

    @Autowired
    private UserService userService;
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @RequestMapping(value="getEasyuiPage")
    public String getEasyuiPage(){
        return "easyui-panel";
        //return "userlist";
    }

    @RequestMapping(value="getUserListPage")
    public String getUserListPage(){
        return "userlist";
    }

    @RequestMapping(value="selectUserList")
    @ResponseBody
    public String selectUserList(Integer page,Integer rows){
        String json = userService.selectUserList((page-1)*rows,rows);
        System.out.println("生产者发送消息*****************"+json);
        send.sendMsg(json);
        return json;
    }

    @RequestMapping("addUserPage")
    public String addUserPage(){
        return "addEditUser";
    }

    @RequestMapping(value="addUser")
    @ResponseBody
    public void addUser(User user){
        userService.addUser(user);
    }

    @RequestMapping(value="deleteUser")
    @ResponseBody
    public void deleteUser(User user){
        userService.deleteUser(user);
    }

    @RequestMapping(value="editQueryUser")
    public ModelAndView editQueryUser(User u){
        Map map = new HashMap<>();
        User user = userService.editQueryUser(u);
        map.put("user", user);
        return new ModelAndView("addEditUser",map);
    }
    
    @RequestMapping(value="testqueen")
    @ResponseBody
    public void testqueen(String test){
    	//rabbitTemplate.convertAndSend("spring-boot-testQueen", test);
    	rabbitTemplate.convertAndSend("osExChange", "", test);
    }

}
