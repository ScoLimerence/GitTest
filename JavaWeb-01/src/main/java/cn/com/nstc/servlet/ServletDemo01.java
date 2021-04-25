package cn.com.nstc.servlet; /**
 * @ClassName ${NAME}.java
 * @author huanghudong
 * @Description TODO
 * @createTime 2021年04月24日 10:48:00
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletDemo01", value = "/ServletDemo01")
public class ServletDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path1 = this.getServletContext().getContextPath();
        String path2 = this.getServletContext().getRealPath("ServletDemo01");
        System.out.println("path1 = " + path1);
        System.out.println("path2 = " + path2);

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/NewServlet");//转发请求的路径
        dispatcher.forward(request,response);//实现转发
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
