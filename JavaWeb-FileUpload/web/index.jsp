<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/5/24
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--通过表单上传文件
    get：上传文件大小有限制
    post：上传文件大小没限制
--%>
<form action="${pageContext.request.contextPath}/upload.do" method="post" enctype="multipart/form-data">
    <p><input type="text" name="uname"></p>
    <p><input type="file" name="file2"></p>
    <p><input type="submit">|<input type="reset"></p>
</form>
</body>
</html>
