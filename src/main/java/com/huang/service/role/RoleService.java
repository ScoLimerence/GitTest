package com.huang.service.role;

import com.huang.pojo.Role;

import java.util.List;

/**
 * @author huanghudong
 * @ClassName RoleService.java
 * @Description TODO
 * @createTime 2021年05月20日 23:53:00
 */
public interface RoleService {

    //查询角色列表
    public List<Role> getRoleList();
}
