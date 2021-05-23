package com.huang.servlet.user;

import com.alibaba.fastjson.JSONArray;
import com.huang.constant.ContextConstant;
import com.huang.pojo.Role;
import com.huang.pojo.User;
import com.huang.service.role.RoleService;
import com.huang.service.role.RoleServiceImpl;
import com.huang.service.user.UserService;
import com.huang.service.user.UserServiceImpl;
import com.huang.utils.PageSupport;
import com.mysql.cj.util.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

    private Logger logger = Logger.getLogger(this.getClass());
    private UserService userService = new UserServiceImpl();
    private RoleService roleService = new RoleServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if ("savepwd".equals(method)) {
            this.updatePwd(req, resp);
        } else if ("pwdmodify".equals(method)) {
            this.pwdModify(req, resp);
        } else if ("query".equals(method)) {
            this.userManage(req, resp);
        }else if ("add".equals(method)){
            this.userAdd(req,resp);
        }else if ("getrolelist".equals(method)){
            this.getRoleList(req,resp);
        }else if ("view".equals(method)){
            this.viewUser(req,resp,"userview.jsp");
        }else if ("modifyexe".equals(method)){
            this.userModify(req,resp);
        }else if ("modify".equals(method)){ ;
            this.viewUser(req,resp,"usermodify.jsp");
        }else if ("deluser".equals(method)){ ;
            this.deleteUser(req,resp);
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

    public void userManage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //请求直接走servlet，什么值都没有,取出来的是null，当进行form表单提交没有内容就是“”空串
        //获取前端传过来的参数
        String queryName = req.getParameter("queryName");
        String queryUserRoleStr = req.getParameter("queryUserRole");
        String pageIndexStr = req.getParameter("pageIndex");

        int queryUserRole = 0;
        int currentPageNo = 1;//当前页
        int pageSize = 5;//每页容量

        if (!StringUtils.isNullOrEmpty(queryUserRoleStr)) {
            queryUserRole = Integer.parseInt(queryUserRoleStr);
        }
        if (!StringUtils.isNullOrEmpty(pageIndexStr)) {
            currentPageNo = Integer.parseInt(pageIndexStr);
        }

        int userTotallount = userService.getUserCount(queryName, queryUserRole);//查询出的用户总数
        List<User> userList = userService.getUserList(queryName, queryUserRole, currentPageNo, pageSize);//得到分页后用户列表
        List<Role> roleList = roleService.getRoleList();

        //分页参数
        PageSupport page = new PageSupport();
        page.setPageSize(pageSize);
        page.setTotalCount(userTotallount);
        page.setCurrentPageNo(currentPageNo);

        //将查询条件带回去
        req.setAttribute("queryName", queryName);
        req.setAttribute("queryUserRole", queryUserRole);
        req.setAttribute("userList", userList);
        req.setAttribute("roleList", roleList);

        req.setAttribute("totalCount",page.getTotalCount());
        req.setAttribute("currentPageNo",page.getCurrentPageNo());
        req.setAttribute("totalPageCount",page.getTotalPageCount());

        req.getRequestDispatcher("/jsp/userlist.jsp").forward(req, resp);
    }

    public void userAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userCode = req.getParameter("userCode");
        String userName = req.getParameter("userName");
        String userPassword = req.getParameter("userPassword");
        String gender = req.getParameter("gender");
        String birthday = req.getParameter("birthday");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String userRole = req.getParameter("userRole");

        User user = new User();
        user.setUserCode(userCode);
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setGender(Integer.valueOf(gender));
        user.setPhone(phone);
        user.setAddress(address);
        user.setUserRole(Integer.valueOf(userRole));
        try {
            user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        boolean b = userService.addUser(user);
        if (b){
            try {
                resp.sendRedirect(req.getContextPath()+"/jsp/user.do?method=query");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            req.getRequestDispatcher("useradd.jsp").forward(req, resp);
        }
    }

    public void getRoleList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Role> roleList = roleService.getRoleList();
        //req.setAttribute("roleList",roleList);
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.write(JSONArray.toJSONString(roleList));
        writer.flush();
        writer.close();
    }

    public void viewUser(HttpServletRequest req, HttpServletResponse resp,String page) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        User user = userService.getUser(Integer.valueOf(uid));
        req.setAttribute("user",user);
        req.getRequestDispatcher(page).forward(req,resp);
    }

    public void userModify(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id = req.getParameter("uid");
        String userName = req.getParameter("userName");
        String gender = req.getParameter("gender");
        String birthday = req.getParameter("birthday");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String userRole = req.getParameter("userRole");

        User user = new User();
        user.setId(Integer.valueOf(id));
        user.setUserName(userName);
        user.setGender(Integer.valueOf(gender));
        try {
            user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        user.setPhone(phone);
        user.setAddress(address);
        user.setUserRole(Integer.valueOf(userRole));
        user.setModifyBy(((User)req.getSession().getAttribute(ContextConstant.USER_SESSION)).getId());
        user.setModifyDate(new Date());

        boolean b = userService.updateUser(user);
        if(b){
            resp.sendRedirect(req.getContextPath()+"/jsp/user.do?method=query");
        }else{
            req.getRequestDispatcher("usermodify.jsp").forward(req, resp);
        }
    }

    public void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String uid = req.getParameter("uid");
        User user = userService.getUser(Integer.valueOf(uid));
        if (user!=null){
            boolean b = userService.deleteUser(Integer.valueOf(uid));
            if (b){
                map.put("delResult","true");
            }else {
                map.put("delResult","false");
            }
        }else {
            map.put("delResult","notexist");
        }
        //把resultMap转换成json对象输出
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.write(JSONArray.toJSONString(map));
        writer.flush();
        writer.close();
    }
}















