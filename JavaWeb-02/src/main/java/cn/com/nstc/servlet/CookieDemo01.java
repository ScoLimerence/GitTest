package cn.com.nstc.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author huanghudong
 * @ClassName CookieDemo01.java
 * @Description TODO
 * @createTime 2021年04月27日 16:36:00
 */
public class CookieDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //服务器给你一个信件，你下次带着信件来，我就知道你来了
        req.setCharacterEncoding("utf-8");//前端传过来中文， 谷歌浏览器默认编码iso-8859-1
        resp.setCharacterEncoding("utf-8");//后台传过去中文

        PrintWriter out = resp.getWriter();

        //获取请求中携带的cookie，原始有很多浏览器自带的，故需要数组接受
        //cookie对象存储的就是键值对，只支持字符串类型的数据name-value
        Cookie[] cookies = req.getCookies();

        //判断cookie是否存在
        if (cookies!=null){
            out.write("你上一次访问的时间是：");
            for (Cookie cookie : cookies) {
                if ("LastTime".equals(cookie.getName())){

                }
            }
        }else {
            out.write("这是第一次访问该网站");//不能给前端传对象
        }

        //服务端响应给客户端一个cookie
        Cookie cookie = new Cookie("LastTime", "hhd");
        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
