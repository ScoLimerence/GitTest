package com.huang.service.role;

import com.huang.dao.BaseDao;
import com.huang.dao.role.RoleDao;
import com.huang.dao.role.RoleDaoImpl;
import com.huang.pojo.Role;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author huanghudong
 * @ClassName RoleServiceImpl.java
 * @Description TODO
 * @createTime 2021年05月20日 23:54:00
 */
public class RoleServiceImpl implements RoleService {

    private final static Logger logger = Logger.getLogger(RoleServiceImpl.class);
    private RoleDao roleDao;

    public RoleServiceImpl() {
        this.roleDao = new RoleDaoImpl();
    }

    @Override
    public List<Role> getRoleList() {
        Connection conn = null;
        List<Role> roleList = null;
        try {
            conn = BaseDao.getConnection();
            roleList = roleDao.getRoleList(conn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResource(conn, null, null);
        }
        return roleList;
    }

    @Test
    public void test01(){
        RoleServiceImpl service = new RoleServiceImpl();
        List<Role> roleList = service.getRoleList();
        for (Role role : roleList) {
            logger.info("role--------->"+role.toString());
        }
    }

}
