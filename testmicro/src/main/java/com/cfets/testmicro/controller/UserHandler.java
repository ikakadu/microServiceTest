package com.cfets.testmicro.controller;



import com.cfets.testmicro.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    User user;

    @GetMapping("/findAll/{page}/{limit}")
    public User findAll(@PathVariable("page") int page, @PathVariable("limit") int limit) {
//        User user = new User();
        user.setAddress("");
        return null;
    }
}
