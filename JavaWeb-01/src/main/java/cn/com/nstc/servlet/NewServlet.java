package cn.com.nstc.servlet; /**
 * @ClassName ${NAME}.java
 * @author huanghudong
 * @Description TODO
 * @createTime 2021年04月24日 10:10:00
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "NewServlet", value = "/NewServlet")
public class NewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //this.getServletContext() 获取上下文  衔接前后端
        ServletContext context = this.getServletContext();
        context.setAttribute("name","黄湖栋");
        response.setHeader("Content-Type","text/html;charset=gbk");
        response.getWriter().println("NewServlet页面");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
