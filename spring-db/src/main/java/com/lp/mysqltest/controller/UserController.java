package com.lp.mysqltest.controller;

import com.lp.mysqltest.entity.User;
import com.lp.mysqltest.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = {"用户测试接口"})
@RestController
@RequestMapping(value = "/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public Map<String, Object> insertUser(User user) {
        Integer integer = userService.insertUser(user);
        Map<String, Object> data = new HashMap<>();
        data.put("count", integer);
        return data;
    }

    @DeleteMapping("/user")
    public Map<String, Object> deleteUser(Long id) {
        Integer integer = userService.deleteUser(id);
        Map<String, Object> data = new HashMap<>();
        data.put("count", integer);
        return data;
    }

    @PutMapping("/user")
    public Map<String, Object> updateUser(User user) {
        Integer integer = userService.updateUser(user);
        Map<String, Object> data = new HashMap<>();
        data.put("count", integer);
        return data;
    }

    @GetMapping("/user")
    public List<User> selectUser(User user) {
        List<User> list = userService.selectUser(user);
        return list;
    }

    @GetMapping("/user/{id}")
    public User selectUserById(Long id) {
        User user = userService.selectUserById(id);
        return user;
    }
}
