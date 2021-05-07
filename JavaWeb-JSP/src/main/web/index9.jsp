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
<%--定义一个变量scope，值为85--%>
<c:set var="scope" value="95"/>

<%--类似于java中switch case语句,执行顺序自上往下，符合条件直接返回不会继续执行--%>
<c:choose>
    <c:when test="${scope>=90}">
        你很优秀
    </c:when>
    <c:when test="${scope>=60}">
        你很一般
    </c:when>
    <c:otherwise><%--但when都不符合的时候执行--%>
        你很low
    </c:otherwise>

</c:choose>
</body>
</html>
