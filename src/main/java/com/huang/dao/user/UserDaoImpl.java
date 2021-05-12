package com.huang.dao.user;

import com.huang.pojo.User;

import java.sql.Connection;

/**
 * @author huanghudong
 * @ClassName UserDaoImpl.java
 * @Description TODO
 * @createTime 2021年05月12日 23:58:00
 */
public class UserDaoImpl implements UserDao{
    @Override
    public User getLoginUser(Connection conn, String userCode) {
        String sql="";
        return null;
    }
}
