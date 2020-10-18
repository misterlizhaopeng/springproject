package com.lp.mysqltest.service.impl;

import com.lp.mysqltest.Dao.UserDao;
import com.lp.mysqltest.entity.User;
import com.lp.mysqltest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Integer insertUser(User user) {
        user.setGmtCreate(new Date());
        Integer i = userDao.insertUser(user);
        return i;
    }

    @Override
    public Integer deleteUser(Long id) {
        return userDao.deleteUser(id);
    }

    @Override
    public Integer updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public List<User> selectUser(User user) {
        return userDao.selectUser(user);
    }

    @Override
    public User selectUserById(Long id) {
        return userDao.selectUserById(id);
    }
}
