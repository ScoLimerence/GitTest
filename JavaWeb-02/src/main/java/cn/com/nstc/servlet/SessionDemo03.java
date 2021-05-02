package cn.com.nstc.servlet;

import cn.com.nstc.pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author huanghudong
 * @ClassName SessionDemo03.java
 * @Description TODO
 * @createTime 2021年05月02日 09:48:00
 */
@WebServlet("/s2")
public class SessionDemo03 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决乱码
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        //得到Session
        HttpSession session = req.getSession();

        Person person = (Person) session.getAttribute("person");

        System.out.println(person.toString());
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
