<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021/5/3
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page errorPage="error/500.jsp" %>&lt;%&ndash;页面有错误就会跳转到错误页面，配置错误的一种方式&ndash;%&gt;--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    int i = 8 / 0;
    System.out.println(i);
%>
</body>
</html>
