package com.huang.service.user;

import com.huang.pojo.User;

import java.util.List;

/**
 * @author huanghudong
 * @ClassName UserService.java
 * @Description TODO
 * @createTime 2021年05月15日 10:05:00
 */
public interface UserService {

    //用户登录
    public User login(String userCode,String userPassword);

    //根据用户id修改用户密码
    public boolean updatePwd(int id,String password);

    //得到分页需要的记录总数
    public int getUserCount(String name,int role);

    //根据条件查询用户列表
    public List<User> getUserList(String name, int role, int currentPageNo, int pageSize);


}
