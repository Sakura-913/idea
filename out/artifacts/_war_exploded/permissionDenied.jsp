<%--
  Created by IntelliJ IDEA.
  User: M
  Date: 2024/12/5
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>权限不足</title>
</head>
<body>
    <h2>权限不足</h2>
    <hr>
    <%
    String errorMessage = (String)request.getAttribute("errorMessage");
    if(errorMessage != null && !errorMessage.isEmpty()) {
    %>
        <p style="color: red;"><%=errorMessage%></p>
    <%
    } else {
    %>
        <p style="color: red;">您没有权限访问此页面！</p>
    <%
    }
    %>
    <br>
    <a href="main.jsp">返回主页面</a>
</body>
</html>
