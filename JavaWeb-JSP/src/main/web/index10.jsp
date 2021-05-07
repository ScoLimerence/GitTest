<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
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
<%
    List<String> peopleList = new ArrayList<>();
    peopleList.add(0,"张三");
    peopleList.add(1,"李四");
    peopleList.add(2,"王五");
    peopleList.add(3,"赵六");
    peopleList.add(4,"田七");
    request.setAttribute("list",peopleList);
%>
<%--
var：每一次遍历出来的遍历
items：要遍历的对象
begin：从索引开始  ------------默认为0
end：到哪儿结束    ----------默认结尾
step：步长       ------默认为1
--%>
<c:forEach var="people" items="${list}">
    <c:out value="${people}"/>
</c:forEach>
<hr>
<c:forEach var="people" items="${list}" begin="1" end="4" step="2">
    <c:out value="${people}"/>
</c:forEach>
</body>
</html>
