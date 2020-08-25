package com.business.dao;

import com.business.model.User;
import com.whiteBox.core.anno.White;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author: kaccn
 * @description:模拟用户数据层增删改查
 * @create: 2020-08-24 09:59
 **/
@Component
public class UserDao {
    private static List<User> locallist = new ArrayList<>();

    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    static {
        User u = new User(0, "元始天尊", "男", "最开始的人");
        locallist.add(u);
    }

    @White(value = "dao层-查询用户", fromIOC = true)
    public User getUser(int id) {
        List<User> users = locallist.stream().filter(u -> u.getUserId() == id).collect(Collectors.toList());
        return users.get(0);
    }

    @White(value = "dao层-用户列表", fromIOC = true)
    public List<User> list() {
        return locallist;
    }

    @White(value = "dao层-新增用户", fromIOC = true)
    public void add(User user) {
        user.setUserId(atomicInteger.getAndIncrement());
        locallist.add(user);
    }

    @White(value = "dao层-修改用户", fromIOC = true)
    public void edit(User user) {
        locallist.removeIf(u -> u.getUserId() == user.getUserId());
        locallist.add(user);
    }

    @White(value = "dao层-删除用户", fromIOC = true)
    public void delete(int id) {
        locallist.removeIf(u -> u.getUserId() == id);
    }
}
