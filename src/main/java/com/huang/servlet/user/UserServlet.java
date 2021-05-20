package com.huang.servlet.user;

import com.alibaba.fastjson.JSONArray;
import com.huang.constant.ContextConstant;
import com.huang.pojo.User;
import com.huang.service.user.UserService;
import com.huang.service.user.UserServiceImpl;
import com.mysql.cj.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huanghudong
 * @ClassName UserServlet.java
 * @Description TODO
 * @createTime 2021年05月16日 23:14:00
 */
//实现Servlet服用，不止修改密码，还有增删改查
//实现复用需要提取出方法
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if ("savepwd".equals(method)) {
            this.updatePwd(req, resp);
        } else if ("pwdmodify".equals(method)) {
            this.pwdModify(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    public void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从session中拿到用户所有信息
        Object o = req.getSession().getAttribute(ContextConstant.USER_SESSION);//可能找不到，尽量先别强转
        String newpassword = req.getParameter("newpassword");
        boolean flag = false;
        if (o != null && !StringUtils.isNullOrEmpty(newpassword)) {//jdbc包提供的String工具类
            UserService userService = new UserServiceImpl();
            flag = userService.updatePwd(((User) o).getId(), newpassword);
            if (flag) {
                //登录成功，移除session
                req.getSession().removeAttribute(ContextConstant.USER_SESSION);
                req.setAttribute("message", "修改密码成功，请退出，使用新密码登录");
            } else {
                //登录失败
                req.setAttribute("message", "密码修改失败");
            }
        } else {
            req.setAttribute("message", "新密码有问题");
        }
        req.getRequestDispatcher("pwdmodify.jsp").forward(req, resp);//相对路径文件同目录下
    }

    public void pwdModify(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //从session中取出用户
        Object o = req.getSession().getAttribute(ContextConstant.USER_SESSION);
        String oldpassword = req.getParameter("oldpassword");

        //用Map封装结果集
        Map<String, Object> resultMap = new HashMap<>();

        if (o == null) {//session过期失效
            resultMap.put("result", "errorSession");
        } else if (StringUtils.isNullOrEmpty(oldpassword)) {//输入密码为空
            resultMap.put("result", "error");
        } else {
            String userPassword = ((User) o).getUserPassword();
            if (oldpassword.equals(userPassword)) {
                resultMap.put("result", "true");
            } else {
                resultMap.put("result", "false");
            }
        }
        //直接向页面输出，通过流的方式
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.write(JSONArray.toJSONString(resultMap));
        writer.flush();
        writer.close();
    }

}
