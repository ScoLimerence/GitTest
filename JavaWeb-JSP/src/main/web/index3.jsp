<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2021/5/3
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%--<%@ include%>这个标签会把两个页面的内容合二为一--%>
    <%@ include file="common/header.jsp" %><%--公共的页面组成部分--%>
    <h1>网页主体</h1>
    <%@include file="common/footer.jsp"%>
    <%--一般不用，淘汰--%>
    <hr>

    <%--JSP标签
    jsp:include本质是拼接页面，还是三个页面，引用
    --%>
    <jsp:include page="/common/header.jsp"/>
    <h1>网页主体</h1>
    <jsp:include page="/common/footer.jsp"/>
</body>
</html>
