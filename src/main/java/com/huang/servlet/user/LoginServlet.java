package com.huang.servlet.user;

import com.huang.constant.ContextConstant;
import com.huang.pojo.User;
import com.huang.service.user.UserService;
import com.huang.service.user.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author huanghudong
 * @ClassName LoginServlet.java
 * @Description TODO
 * @createTime 2021年05月15日 11:47:00
 */
public class LoginServlet extends HttpServlet {
    private final static Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("LoginServlet-------start");

        //获取前端参数
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");

        //和数据库中密码进行对比调用业务层
        UserService userService = new UserServiceImpl();
        User user = userService.login(userCode, userPassword);//把数据库中正确登录的用户返回

        if (user!=null){
            req.getSession().setAttribute(ContextConstant.USER_SESSION,user);
            resp.sendRedirect("jsp/frame.jsp");
        }else {
            req.setAttribute("error","用户名或密码错误");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
