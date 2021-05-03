<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    //统一从pageContext中取出，通过寻找的方式，这依托于JVM双亲委派机制，pageContext是最底层。
    String name1 = (String) pageContext.findAttribute("name1");
    String name2 = (String) pageContext.findAttribute("name2");
    String name3 = (String) pageContext.findAttribute("name3");
    String name4 = (String) pageContext.findAttribute("name4");
    String name5 = (String) pageContext.findAttribute("name5");
%>
<%--使用EL表达式输出对象--%>
<h1>取出的值为：</h1>
<h3>${name1}</h3>
<h3>${name2}</h3><--request没有转发，故作用域失效取不到-->
<h3>${name3}</h3>
<h3>${name4}</h3>
<h3>${name5}</h3>
</body>
</html>
