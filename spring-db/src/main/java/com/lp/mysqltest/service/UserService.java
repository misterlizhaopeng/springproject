package com.lp.mysqltest.service;

import com.lp.mysqltest.entity.User;

import java.util.List;

public interface UserService {

    Integer insertUser(User user);

    Integer deleteUser(Long id);

    Integer updateUser(User user);

    List<User> selectUser(User user);

    User selectUserById(Long id);
}
