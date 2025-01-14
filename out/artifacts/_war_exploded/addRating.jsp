<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加评分</title>
</head>
<body>
    <h2>添加评分</h2>
    <a href="allRatings">返回列表</a>
    <hr>

    <form action="addRating" method="post">
        电影名称：<input type="text" name="movieName" required><br><br>
        工作室名称：<input type="text" name="studioName" required><br><br>
        评分：<input type="number" name="ratingScore" min="0" max="10" step="0.1" required><br>
        <small>评分范围：0-10分，可精确到小数点后一位</small><br><br>
        <input type="submit" value="提交">
        <input type="button" value="返回" onclick="history.back()">
    </form>

    <%-- 显示错误信息 --%>
    <% 
    String error = (String)request.getAttribute("error");
    if(error != null && !error.isEmpty()) {
    %>
        <p style="color: red;"><%=error%></p>
    <%
    }
    %>
</body>
</html>