package com.huang.dao.user;

import com.huang.pojo.User;

import java.sql.Connection;

/**
 * @author huanghudong
 * @ClassName UserDao.java
 * @Description 面向接口编程
 * @createTime 2021年05月12日 23:55:00
 */
public interface UserDao {

    //得到登录的用户
    public User getLoginUser(Connection conn,String userCode);
}
