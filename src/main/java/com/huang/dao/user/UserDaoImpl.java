package com.huang.dao.user;

import com.huang.dao.BaseDao;
import com.huang.pojo.Role;
import com.huang.pojo.User;
import com.mysql.cj.util.StringUtils;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huanghudong
 * @ClassName UserDaoImpl.java
 * @Description TODO
 * @createTime 2021年05月12日 23:58:00
 */
public class UserDaoImpl implements UserDao {
    Logger logger = Logger.getLogger(this.getClass());

    @Override
    public User getLoginUser(Connection conn, String userCode) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        if (conn != null) {
            String sql = "select * from smbms_user t where t.userCode=?";
            Object[] params = {userCode};

            rs = BaseDao.universalQuery(conn, sql, ps, params, rs);

            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
                user.setUserCode(rs.getString(2));
                user.setUserName(rs.getString(3));
                user.setUserPassword(rs.getString(4));
                user.setGender(rs.getInt(5));
                user.setBirthday(rs.getDate(6));
                user.setPhone(rs.getString(7));
                user.setAddress(rs.getString(8));
                user.setUserRole(rs.getInt(9));
                user.setCreatedBy(rs.getInt(10));
                user.setCreationDate(rs.getDate(11));
                user.setModifyBy(rs.getInt(12));
                user.setModifyDate(rs.getDate(13));
            }
            BaseDao.closeResource(conn, ps, rs);
        }
        return user;
    }

    @Override
    public int updatePwd(Connection conn, int id, String password) throws SQLException {
        PreparedStatement ps = null;
        int row = 0;
        if (conn != null) {
            String sql = "update smbms_user set userPassword= ? where id= ?";
            Object[] param = {password, id};//数组定义方式
            row = BaseDao.universalUpdate(conn, sql, ps, param);
            //BaseDao.closeResource(conn,ps,null);
        }
        return row;
    }

    @Override
    public int getUserCount(Connection conn, String name, int role) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        if (conn != null) {
            StringBuilder sql = new StringBuilder();
            sql.append("select COUNT(1) as count  from smbms_user su LEFT JOIN smbms_role sr on su.userRole=sr.id where 1=1");
            List<Object> params = new ArrayList<>();//存放一些复杂的参数

            if (!StringUtils.isNullOrEmpty(name)) {
                sql.append(" and su.userName like ?");//%也是占位符不能直接拼接
                params.add("%" + name + "%");
            }
            if (role > 0) {
                sql.append(" and sr.id = ?");
                params.add(role);
            }
            //把参数list转为数组
            Object[] arr = params.toArray();
            System.out.println("UserDao.getUserCount->sql:" + sql.toString());

            rs = BaseDao.universalQuery(conn, sql.toString(), ps, arr, rs);
            if (rs.next()) {
                count = rs.getInt("count");
            }
            BaseDao.closeResource(conn, ps, rs);
        }
        return count;
    }

    @Override
    public List<User> getUserList(Connection conn, String name, int role, int currentPageNo, int pageSize) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        List<User> userList = new ArrayList<>();
        if (conn != null) {
            StringBuilder sql = new StringBuilder();
            sql.append("select * from smbms_user su LEFT JOIN smbms_role sr on su.userRole=sr.id where 1=1");
            List<Object> params = new ArrayList<>();//存放一些复杂的参数

            if (!StringUtils.isNullOrEmpty(name)) {
                sql.append(" and su.userName like ?");//%也是占位符不能直接拼接
                params.add("%" + name + "%");
            }
            if (role > 0) {
                sql.append(" and sr.id = ?");
                params.add(role);
            }

            /**
             * 在MySQL中分页使用limit
             * 起始记录= （当前页-1）*每页大小
             */
            sql.append(" order by su.creationDate desc limit ?,?");
            int startIndex = (currentPageNo - 1) * pageSize;
            params.add(startIndex);
            params.add(pageSize);
            Object[] arr = params.toArray();

            logger.info("sql----->" + sql);
            rs = BaseDao.universalQuery(conn, sql.toString(), ps, arr, rs);
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
                user.setUserCode(rs.getString(2));
                user.setUserName(rs.getString(3));
                user.setUserPassword(rs.getString(4));
                user.setGender(rs.getInt(5));
                user.setBirthday(rs.getDate(6));
                user.setPhone(rs.getString(7));
                user.setAddress(rs.getString(8));
                user.setUserRole(rs.getInt(9));
                user.setCreatedBy(rs.getInt(10));
                user.setCreationDate(rs.getDate(11));
                user.setModifyBy(rs.getInt(12));
                user.setModifyDate(rs.getDate(13));
                userList.add(user);
            }
            BaseDao.closeResource(conn, ps, rs);
        }
        return userList;
    }


}
