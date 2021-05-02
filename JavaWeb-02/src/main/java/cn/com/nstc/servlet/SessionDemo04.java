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
 * @ClassName SessionDemo04.java
 * @Description TODO
 * @createTime 2021年05月02日 09:56:00
 */
@WebServlet("/s3")
public class SessionDemo04 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决乱码
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        HttpSession session = req.getSession();
        session.removeAttribute("person");
        //手动注销session，重新访问服务器时，会自动生成新的session
        session.invalidate();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
