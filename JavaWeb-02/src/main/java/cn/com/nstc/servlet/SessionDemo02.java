package cn.com.nstc.servlet;

import cn.com.nstc.pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author huanghudong
 * @ClassName SessionDemo02.java
 * @Description TODO
 * @createTime 2021年05月02日 09:22:00
 */
@WebServlet("/s1")
public class SessionDemo02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决乱码
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        //得到Session
        HttpSession session = req.getSession();
        //给Session存东西
        session.setAttribute("person",new Person("hhd",21));
        //获取SessionID
        String id = session.getId();

        //判断Session是不是新创建
        if (session.isNew()){
            resp.getWriter().write("session创建成功，id："+id);
        }else {
            resp.getWriter().write("session在服务器中已经存在，id："+id);
        }
        //Session创建的时候做的事情,访问服务器时自动执行
        /*Cookie cookie = new Cookie("JSESSIONID",id);
        resp.addCookie(cookie);*/
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
