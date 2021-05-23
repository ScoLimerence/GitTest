package com.huang.service.user;

import com.huang.dao.BaseDao;
import com.huang.dao.user.UserDao;
import com.huang.dao.user.UserDaoImpl;
import com.huang.pojo.User;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author huanghudong
 * @ClassName UserServiceImpl.java
 * @Description TODO
 * @createTime 2021年05月15日 10:06:00
 */
public class UserServiceImpl implements UserService {

    private final static Logger logger = Logger.getLogger(UserServiceImpl.class);

    //业务层都会调用DAO层
    private UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    @Override
    public User login(String userCode, String userPassword) {
        Connection conn = null;
        User user = null;

        try {
            conn = BaseDao.getConnection();
            //通过业务层调用具体数据库操作
            user = userDao.getLoginUser(conn, userCode);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResource(conn, null, null);
        }
        return user;
    }

    @Override
    public boolean updatePwd(int id, String password) {
        //业务层存在事务 需要进行事务回滚
        Connection conn = null;
        boolean flag = false;
        try {
            conn = BaseDao.getConnection();
            conn.setAutoCommit(false);

            if (userDao.updatePwd(conn, id, password) > 0) {
                flag = true;
                conn.commit();//不能提前把连接关闭，否则提交事务失败报错
            }
        } catch (SQLException throwables) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResource(conn, null, null);
        }
        return flag;
    }

    @Override
    public int getUserCount(String name, int role) {

        Connection conn = null;
        int count = 0;
        try {
            conn = BaseDao.getConnection();
            count = userDao.getUserCount(conn, name, role);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResource(conn, null, null);
        }
        return count;
    }

    @Override
    public List<User> getUserList(String name, int role, int currentPageNo, int pageSize) {
        Connection conn = null;
        List<User> userList = null;

        try {
            conn = BaseDao.getConnection();
            userList = userDao.getUserList(conn, name, role, currentPageNo, pageSize);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResource(conn, null, null);
        }
        return userList;
    }

    @Override
    public boolean addUser(User user) {
        Connection conn = null;
        boolean flag = false;
        try {
            conn = BaseDao.getConnection();
            conn.setAutoCommit(false);
            int rows = userDao.addUser(conn, user);
            conn.commit();
            if (rows > 0) {
                flag = true;
                System.out.println("add success!");
            } else {
                System.out.println("add failed!");
            }
        } catch (SQLException throwables) {
            try {
                System.out.println("rollback==================");
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        } finally {
            BaseDao.closeResource(conn, null, null);
        }
        return flag;
    }

    @Override
    public User getUser(Integer uId) {
        Connection conn = null;
        User user = null;
        try {
            conn = BaseDao.getConnection();
            user = userDao.getUser(conn, uId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;
    }

    @Override
    public boolean updateUser(User user) {
        Connection conn = null;
        int row = 0 ;
        boolean flag = false;
        try {
            conn = BaseDao.getConnection();
            conn.setAutoCommit(false);
            row = userDao.updateUser(conn, user);
            conn.commit();
            if (row>0){
                flag=true;
            }
        } catch (SQLException throwables) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(conn,null,null);
        }
        return flag;
    }

    @Override
    public boolean deleteUser(Integer uId) {
        Connection conn = null;
        int row = 0 ;
        boolean flag = false;
        try {
            conn = BaseDao.getConnection();
            conn.setAutoCommit(false);
            row = userDao.deleteUser(conn, uId);
            conn.commit();
            if (row>0){
                flag=true;
            }
        } catch (SQLException throwables) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }finally {
            BaseDao.closeResource(conn,null,null);
        }
        return flag;
    }

    @Test
    public void test() {
        UserServiceImpl service = new UserServiceImpl();
        /*boolean b = service.updatePwd(1, "123456");
        logger.info("flag------>" + b);*/


        List<User> userList = service.getUserList(null, 0, 2, 3);
        for (User user : userList) {
            logger.info(user.toString());
        }
    }

}
