<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021/5/3
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%--页面中请求转发--%>
    <%
        pageContext.forward("/index4.jsp");
        //request.getRequestDispatcher("").forward(request,response);很少使用，一般页面跳转使用第一种方法
    %>
</body>
</html>
