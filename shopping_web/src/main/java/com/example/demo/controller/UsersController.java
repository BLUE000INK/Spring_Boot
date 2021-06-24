package com.example.demo.controller;

import com.example.demo.entity.Info;
import com.example.demo.entity.Stores;
import com.example.demo.entity.Users;
import com.example.demo.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Controller
//log API接口
@Slf4j
@RequestMapping("/users")
public class UsersController {
    //自动装解
    @Autowired
    private UsersMapper usersMapper;
    @PostMapping("/findUser")
    @ResponseBody
    public int finduser(@RequestBody Users user){
        log.info(user.getUserName() + " 在尝试登录..... " + user.getUserPwd());
        log.info("selectbyname"+usersMapper.selectByName("zwj"));
            if(usersMapper.selectByName(user.getUserName()) == null){
                log.info("该用户不存在");
                return 2;
            }else if(usersMapper.selectByName(user.getUserName()).getUserPwd().equals(user.getUserPwd())){
                log.info("登录成功");
                return 1;
            }else{
                log.info("密码错误");
                return 0;
            }
    }

    @PostMapping("/findUserinfo")
    @ResponseBody
    public Users findUserinfo(@RequestBody Users user){
        log.info("name:"+user.getUserName());
        log.info("bank="+usersMapper.selectByName(user.getUserName()).getUserBank());
        return usersMapper.selectByName(user.getUserName());
    }

    @PostMapping("/insertUser")
    @ResponseBody
    public Users insertUser(@RequestBody Users user){
        log.info("userregistinfo+" + user);
        log.info("count"+usersMapper.userCount());
        user.setUserId(usersMapper.selectidMAX() + 1 );
        user.setUserCredit(5);
        user.setUserAdmin(0);
        user.setUserScore(0);
        user.setUserMoney(100);
        usersMapper.insert(user);
        return user;
    }

    @PostMapping("/myupdate")
    @ResponseBody
    public int myupdate(@RequestBody Users user){
        log.info("update+"+user.getUserId());
        return usersMapper.myupdate(user);
    }

    @RequestMapping("/getAllUser")
    @ResponseBody
    public List<Users> getAllUser(){
        return usersMapper.getAllUser();
    }

    @PostMapping("/DeletUser")
    @ResponseBody
    public int DeletUser(@RequestBody Users users){
        return usersMapper.deleteByPrimaryKey(users.getUserId());
    }

    @PostMapping("/UserCredit21")
    @ResponseBody
    public int UserCredit21(@RequestBody Users users){
        return usersMapper.UserCredit21(users);
    }

    @PostMapping("/UserCredit22")
    @ResponseBody
    public int UserCredit22(@RequestBody Users users){
        return usersMapper.UserCredit22(users);
    }
}
