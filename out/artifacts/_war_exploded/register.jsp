<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
</head>
<body>
    <h2>用户注册</h2>
    <hr>
    <form action="register" method="post">
        用户名：<input type="text" name="username" required><br><br>
        密码：<input type="password" name="password" required><br><br>
        用户类型：
        <select name="role" required>
            <option value="user">普通用户</option>
            <option value="admin">管理员</option>
        </select><br><br>
        <input type="submit" value="注册">
    </form>
    <br>
    <a href="login">返回登录</a>

</body>
</html>
