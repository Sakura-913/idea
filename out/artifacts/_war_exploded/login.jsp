<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
</head>
<body>
    <h2>用户登录</h2>
    <form action="login" method="post">
        用户名：<input type="text" name="username" ><br>
        密码：<input type="password" name="password" ><br>
        <input type="submit" value="登录">
    </form>
    <br>
    <a href="register.jsp">点击注册</a>
</body>
</html>