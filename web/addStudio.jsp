<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加工作室</title>
</head>
<body>
    <h2>添加工作室</h2>
    <a href="allStudios">返回列表</a>
    <hr>

    <form action="addStudio" method="post">
        工作室名称：<input type="text" name="studioName" required><br><br>
        地点：<input type="text" name="location" required><br><br>
        成立年份：<input type="number" name="foundYear" required><br><br>
        描述：<br>
        <textarea name="description" rows="3" cols="50" required></textarea><br><br>
        <input type="submit" value="提交">
        <input type="button" value="返回" onclick="history.back()">
    </form>
</body>
</html> 