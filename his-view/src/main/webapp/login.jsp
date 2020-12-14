<%--
  Created by IntelliJ IDEA.
  User: spring
  Date: 20-12-10
  Time: 下午12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <script src="webjars/layui/2.5.5/layui.js"></script>
</head>
<body>
<h1>${exception}</h1>
<form action="/login" method="post">
    <input type="text" name="loginName" value="admin">
    <input type="text" name="password" value="12345">
    <button type="submit">login</button>
</form>
</body>
</html>
