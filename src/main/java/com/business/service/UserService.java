package com.business.service;

import com.business.dao.UserDao;
import com.business.model.User;
import com.whiteBox.core.anno.White;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: kaccn
 * @description: 用户服务
 * @create: 2020-08-24 09:37
 **/
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @White(value = "service层-查询用户", fromIOC = true)
    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @White(value = "service层-用户列表", fromIOC = true)
    public List<User> list() {
        return userDao.list();
    }

    @White(value = "service层-新增用户", fromIOC = true)
    public void add(User user) {
        userDao.add(user);
    }

    @White(value = "service层-修改用户", fromIOC = true)
    public void edit(User user) {
        userDao.edit(user);
    }

    @White(value = "service层-删除用户", fromIOC = true)
    public void delete(int id) {
        userDao.delete(id);
    }
}
