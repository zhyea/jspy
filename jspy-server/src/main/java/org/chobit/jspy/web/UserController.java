package org.chobit.jspy.web;

import org.chobit.jspy.service.beans.User;
import org.chobit.jspy.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;


    @GetMapping("/users")
    public List<User> hello() {
        return userMapper.findAll();
    }

}
