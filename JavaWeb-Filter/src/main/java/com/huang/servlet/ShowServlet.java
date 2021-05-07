package com.huang.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author huanghudong
 * @ClassName ShowServlet.java
 * @Description TODO
 * @createTime 2021年05月07日 15:49:00
 */

@WebServlet("/showServlet")
public class ShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.setHeader("content-type","text/html;charset=utf-8");//同时设置编码与解码字符集
        //resp.setCharacterEncoding("gbk");//只设置编码字符集，解码交给tomcat
        resp.getWriter().write("初识过滤器。。。。。");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
