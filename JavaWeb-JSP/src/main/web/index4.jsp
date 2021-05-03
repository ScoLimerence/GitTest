<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021/5/3
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--内置对象--%>
<%
    //代指当前页面作用域，能在其它三个中转换
    pageContext.setAttribute("name1", "hhd1");//保存的数据只在一个页面中有效
    //request没有转发，故作用域失效取不到
    request.setAttribute("name2", "hhd2");//保存的数据在一次请求中有效，请求转发会携带这个东西
    session.setAttribute("name3", "hhd3");//保存的数据在一次会话中有效，从打开浏览器到关闭浏览器
    application.setAttribute("name4", "hhd4");//保存的数据只在服务器中有效，打开服务器到关闭服务器
%>
<%
    //统一从pageContext中取出，通过寻找的方式，这依托于JVM双亲委派机制，pageContext是最底层。
    String name1 = (String) pageContext.findAttribute("name1");
    String name2 = (String) pageContext.findAttribute("name2");
    String name3 = (String) pageContext.findAttribute("name3");
    String name4 = (String) pageContext.findAttribute("name4");
    String name5 = (String) pageContext.findAttribute("name5");
    //pageContext.forward("/pageContextDemo01.jsp"); 转发之后request种存的东西就能取到
%>
<%--使用EL表达式输出对象--%>
<h1>取出的值为：</h1>
<h3>${name1}</h3>
<h3>${name2}</h3>
<h3>${name3}</h3>
<h3>${name4}</h3>
<h3>${name5}</h3>
</body>
</html>
