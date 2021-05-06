<%@ page import="com.huang.pojo.People" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021/5/3
  Time: 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    //People people = new People();
    //people.setAddr();
    //people.setName();
    //people.setAge();
    //people.getName();
    //people.getAddr();
    //people.getName();
%>

<%--这个jsp标签等价于上面的java代码--%>
<jsp:useBean id="people" class="com.huang.pojo.People" scope="page"/>

<jsp:setProperty name="people" property="addr" value="浠水县"/>
<jsp:setProperty name="people" property="name" value="hhd"/>
<jsp:setProperty name="people" property="age" value="18"/>


姓名：<jsp:getProperty name="people" property="name"/><br>
年龄：<jsp:getProperty name="people" property="age"/><br>
地址：<jsp:getProperty name="people" property="addr"/>
</body>
</html>
