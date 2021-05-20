package com.huang.dao;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author huanghudong
 * @ClassName BaseDao1.java
 * @Description TODO
 * @createTime 2021年05月12日 21:30:00
 */
public class BaseDao {
    public final static Logger logger = Logger.getLogger(BaseDao.class);

    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    static {
        logger.info("------------------->静态代码块执行");
        //读取配置文件，利用反射机制通过类加载器读取文件，获得流对象
        Properties prop = new Properties();
        InputStream in = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = prop.getProperty("driver");
        url = prop.getProperty("url");
        username = prop.getProperty("username");
        password = prop.getProperty("password");
    }

    {
        logger.info("------------------->构造代码块执行");
    }

    public BaseDao() {
        logger.info("------------------->构造函数执行");
    }

    //获得数据库连接
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 通用查询方法
     *
     * @param conn
     * @param sql
     * @param ps
     * @param params 参数数组
     * @param rs
     */
    public static ResultSet universalQuery(Connection conn, String sql, PreparedStatement ps, Object[] params, ResultSet rs) throws SQLException {
        ps = conn.prepareStatement(sql);

        //数组从0开始，占位符从1开始
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }
        rs = ps.executeQuery();
        return rs;
    }

    /**
     * 通用增、删、改方法
     *
     * @param conn
     * @param sql
     * @param ps
     * @param params
     * @return
     * @throws SQLException
     */
    public static int universalUpdate(Connection conn, String sql, PreparedStatement ps, Object[] params) throws SQLException {
        ps = conn.prepareStatement(sql);//读取预编译sql语句

        //数组从0开始，占位符从1开始
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }
        int column = ps.executeUpdate();
        return column;
    }

    //释放资源
    public static boolean closeResource(Connection conn, PreparedStatement ps, ResultSet rs) {

        boolean flag = true;
        if (rs != null) {
            try {
                rs.close();
                //如果还是无法关闭，通知GC回收
                rs = null;
            } catch (SQLException throwables) {
                flag = false;
                throwables.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
                //如果还是无法关闭，通知GC回收
                ps = null;
            } catch (SQLException throwables) {
                flag = false;
                throwables.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
                //如果还是无法关闭，通知GC回收
                conn = null;
            } catch (SQLException throwables) {
                flag = false;
                throwables.printStackTrace();
            }
        }

        return flag;
    }
}
