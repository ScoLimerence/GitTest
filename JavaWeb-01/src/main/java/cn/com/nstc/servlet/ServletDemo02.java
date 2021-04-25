package cn.com.nstc.servlet;

import com.sun.org.apache.bcel.internal.util.ClassPath;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author huanghudong
 * @ClassName ServletDemo02.java
 * @Description TODO
 * @createTime 2021年04月24日 11:27:00
 */

@WebServlet("/ServletDemo02")
public class ServletDemo02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * ServletContext. getResourceAsStream(String path) 默认的相对路径是
         * 工程目录  下，如果文件在SRC下，那么我们可以这么写path  :  /WEB-INF/classes/xxx.file
         */
        InputStream is = this.getServletContext().getResourceAsStream("/WEB-INF/classes/db.properties");
        Properties properties = new Properties();
        properties.load(is);
        String name = properties.getProperty("username");
        String pwd = properties.getProperty("password");
        response.getWriter().println("pwd = " + pwd);
        response.getWriter().println("name = " + name);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
