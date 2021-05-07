<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--引入其它页面--%>
<%--<jsp:include page="index5.jsp"/>--%>

<%--页面携带参数转发，实际带参是request--%>
<jsp:forward page="index7.jsp">
    <jsp:param name="name" value="huang"/>
    <jsp:param name="age" value="21"/>
</jsp:forward>
</body>
</html>