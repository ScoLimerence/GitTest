<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021/5/3
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ page errorPage="error/500.jsp" %>&lt;%&ndash;页面有错误就会跳转到错误页面，配置错误的一种方式&ndash;%&gt;--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4>测试</h4>
<hr>
<%--表单提交到自身页面--%>
<form action="index8.jsp" method="get">
    <%--
    EL表达式获取表单中的数据
    ${param.参数名}
    ${param.username}相当于文本框中输入的值赋给它
    --%>
    <input type="text" name="username" value="${param.username}">
    <input type="submit" value="登录">
</form>

<c:if test="${param.username=='admin'}" var="isAdmin">
    <%--var用于存储条件结果的变量,本质是一个bool值用isAdmin对象指代
    c:out相当于<%=%>
    --%>
    <c:out value="管理员欢迎你！"/>

</c:if>

<c:out value="${isAdmin}"/>
</body>
</html>
