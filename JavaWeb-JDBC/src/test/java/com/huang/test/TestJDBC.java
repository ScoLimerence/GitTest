package com.huang.test;

import org.junit.Test;

import java.sql.*;

/**
 * @author huanghudong
 * @ClassName TestJDBC.java
 * @Description TODO
 * @createTime 2021年05月09日 23:45:00
 */
public class TestJDBC {

    @Test
    public void testConn() throws ClassNotFoundException, SQLException {
        //连接驱动配置信息
        //useUnicode=true&characterEncoding=gbk 解决中文乱码
        String url="jdbc:mysql://localhost:3308/jdbc_test?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
        String username="root";
        String password="123456";

        //加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //连接数据库  相当于数据库对象
        Connection conn = DriverManager.getConnection(url, username, password);

        Statement sm = conn.createStatement();
        String sql ="select * from users";
        //增、删、改都使用executeUpdate
        ResultSet rs = sm.executeQuery(sql);
        while (rs.next()){
            //数据库列索引从1 开始
            System.out.println("id="+rs.getObject(1));
            System.out.println("name="+rs.getObject(2));
            System.out.println("password="+rs.getObject(3));
            System.out.println("email="+rs.getObject(4));
            System.out.println("birthday="+rs.getObject(5));
        }

        //先开后关
        rs.close();
        sm.close();
        conn.close();
    }


}
