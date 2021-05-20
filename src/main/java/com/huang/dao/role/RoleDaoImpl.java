package com.huang.dao.role;

import com.huang.dao.BaseDao;
import com.huang.pojo.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huanghudong
 * @ClassName RoleDaoImpl.java
 * @Description TODO
 * @createTime 2021年05月20日 23:52:00
 */
public class RoleDaoImpl implements RoleDao{

    @Override
    public List<Role> getRoleList(Connection conn) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Role role = null;
        List<Role> roleList = new ArrayList<>();

        if (conn != null) {
            String sql = "select * from smbms_role";
            Object[] params = {};
            rs = BaseDao.universalQuery(conn, sql, ps, params, rs);
            while (rs.next()){
                role = new Role();
                role.setId(rs.getInt("id"));
                role.setRoleCode(rs.getString("roleCode"));
                role.setRoleName(rs.getString("roleName"));
                roleList.add(role);
            }
            BaseDao.closeResource(conn,ps,rs);
        }
        return roleList;
    }
}
