package com.huang.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @author huanghudong
 * @ClassName CookieDemo.java
 * @Description TODO
 * @createTime 2021年04月28日 23:23:00
 */
public class CookieDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //服务器告诉你来的时间，把这个时间装成一个信件，你下次访问带着信件，就证明你来过

        //解决前端与后端中文传输乱码
        req.setCharacterEncoding("UTF-8");
        //这句话的意思，是让浏览器用utf8来解析返回的数据
        resp.setHeader("Content-Type", "text/html;charset=UTF-8");
        //这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();

        //服务器端从客户端获取cookies
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            out.write("你上次访问的时间是：");
            for (Cookie cookie : cookies) {//cookie的本质是一个map，限制key和value都是String类型
                if ("lastTime".equals(cookie.getName())) {
                    long lastTime = Long.parseLong(cookie.getValue());
                    Date date = new Date(lastTime);
                    out.print(date.toLocaleString());
                }
            }
        } else {
            out.write("这是你第一次访问本站！");
        }

        Cookie cookie = new Cookie("lastTime", System.currentTimeMillis() + "");
        //给cookie设置有效期
        cookie.setMaxAge(3);//会话结束后cookie依然存在，下次会话还能直接访问，单位：秒
        resp.addCookie(cookie);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
