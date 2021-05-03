<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021/5/3
  Time: 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
JSP
<%--JSP表达式
作用：用来将程序的输出打印到客户端
<%= new java.util.Date()%>
--%>
<%= new java.util.Date()%>
<hr>

<%--jsp脚本片段--%>
<%
    int sum = 0;
    for (int i = 0; i < 100; i++) {
        sum += i;
    }
    out.println("<span>Sum=" + sum + "</span>");
%>

<%--在代码里嵌入HTML元素--%>
<%
    for (int i = 0; i < 5; i++) {
%>
<h2>hello,world <%= new java.util.Date()%></h2>
<%
    }
%>

<%--JSP声明--%>
<%!
    static {
        System.out.println("loading JSP Page");
    }

    private int flag=0;

    public void hunag(){
        System.out.println("进入了方法黄");
    }

%>

<%--这是JSP注释--%>
<!--这是HTML注释-->
</body>
</html>
