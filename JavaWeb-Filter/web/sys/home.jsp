<%@ page import="com.huang.constant.Constant" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/5/9
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    //判断用户是否登录、实现拦截效果，不利于职责分离
/*    Object userSession = request.getSession().getAttribute(Constant.USER_SESSION);
    if (userSession==null){
        pageContext.forward("/login.jsp");
    }*/
//    交给过滤器
%>
<h1><span>主页</span></h1>
<h1><span><a href="/loginOutServlet">注销</a></span></h1>
</body>
</html>
