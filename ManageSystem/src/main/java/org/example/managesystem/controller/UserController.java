package org.example.managesystem.controller;

import org.example.managesystem.entity.User;
import org.example.managesystem.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserMapper userMapper;
    @RequestMapping("/login")
    public User Login(@RequestBody User user){
        return userMapper.getUserByName(user);
    }

    @RequestMapping("/passtoken")
    public String getUserNameFromToken(User user){
        return userMapper.getUserNameAsToken(user.getName());
    }
}
