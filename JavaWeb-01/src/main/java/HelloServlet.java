import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author huanghudong
 * @ClassName HelloServlet.java
 * @Description TODO
 * @createTime 2021年04月23日 15:02:00
 */
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext context = this.getServletContext();
        String name = (String) context.getAttribute("name");
/*        resp.setContentType("text/html");
        resp.setCharacterEncoding("gbk");*/
        resp.setHeader("Content-Type","text/html;charset=gbk");
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>你好 javaWeb!</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>name=" + name + "</h1>");
        writer.println("</body>");
        writer.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
