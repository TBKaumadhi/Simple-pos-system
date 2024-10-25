/*package com.myproject.POS_System.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.POS_System.entity.User;
import com.myproject.POS_System.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired private UserService userService;

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        
        
        return userService.createUser(user);
    }
    
}/* */
