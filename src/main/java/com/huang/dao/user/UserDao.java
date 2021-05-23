package com.huang.dao.user;

import com.huang.pojo.Role;
import com.huang.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author huanghudong
 * @ClassName UserDao.java
 * @Description 面向接口编程
 * @createTime 2021年05月12日 23:55:00
 */
public interface UserDao {

    //得到登录的用户
    public User getLoginUser(Connection conn,String userCode) throws SQLException;

    //修改用户密码
    public int updatePwd(Connection conn,int id,String password) throws SQLException;

    //根据用户名和角色查询用户总数
    public int getUserCount(Connection conn,String name,int role) throws SQLException;

    //将查询与分页结合得到用户列表
    public List<User> getUserList(Connection conn,String name,int role,int currentPageNo,int pageSize) throws SQLException;

    //添加用户
    public int addUser(Connection conn,User user) throws SQLException;

    //通过用户id得到用户
    public User getUser(Connection conn , Integer uId) throws SQLException;

    //通过用户id修改用户信息
    public int updateUser(Connection conn , User user) throws SQLException;

    //根据用户id删除用户
    public int deleteUser(Connection conn ,Integer uId) throws SQLException;
}
