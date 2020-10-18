package com.lp.mysqltest.Dao;

import com.lp.mysqltest.DataSource.DBTypeEnum;
import com.lp.mysqltest.DataSource.DataSource;
import com.lp.mysqltest.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "userDao")
public interface UserDao {

    @DataSource(type = DBTypeEnum.MASTER)
    Integer insertUser(User user);

    @DataSource(type = DBTypeEnum.MASTER)
    Integer deleteUser(Long id);

    @DataSource(type = DBTypeEnum.MASTER)
    Integer updateUser(User user);

    @DataSource(type = DBTypeEnum.SLAVE)
    List<User> selectUser(User user);

    @DataSource(type = DBTypeEnum.SLAVE)
    User selectUserById(Long id);
}
