package com.huang.dao.role;

import com.huang.pojo.Role;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author huanghudong
 * @ClassName RoleDao.java
 * @Description TODO
 * @createTime 2021年05月20日 23:51:00
 */
public interface RoleDao {

    //查询角色列表
    public List<Role> getRoleList(Connection conn) throws SQLException;
}
