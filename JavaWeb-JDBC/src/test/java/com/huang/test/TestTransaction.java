package com.huang.test;

import org.junit.Test;

import java.sql.*;

/**
 * @author huanghudong
 * @ClassName TestTransaction.java
 * @Description TODO
 * @createTime 2021年05月11日 21:30:00
 */
public class TestTransaction {

    @Test
    public void testTransaction() {
        //连接驱动配置信息
        //useUnicode=true&characterEncoding=gbk 解决中文乱码
        String url = "jdbc:mysql://localhost:3308/jdbc_test?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
        String username = "root";
        String password = "123456";

        //连接数据库  相当于数据库对象
        Connection conn = null;
        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            //JDBC开启事务
            conn.setAutoCommit(false);
            Statement sm = conn.createStatement();
            String sql1 = "update account set money=money-100 where name='A'";
            sm.executeUpdate(sql1);
            int i = 1 / 0;
            String sql2 = "update account set money=money+100 where name='B'";
            sm.executeUpdate(sql2);
            conn.commit();
            //先开后关
            sm.close();
            conn.close();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }
    }

}
