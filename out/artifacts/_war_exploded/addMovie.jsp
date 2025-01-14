<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加电影</title>
</head>
<body>
    <h2>添加电影</h2>
    <a href="allMovies">返回列表</a>
    <hr>

    <form action="addMovie" method="post">
        电影名称：<input type="text" name="movieName" required><br><br>
        导演：<input type="text" name="director" required><br><br>
        演员：<input type="text" name="actors" required><br><br>
        上映年份：<input type="number" name="releaseYear" required><br><br>
        类型：<input type="text" name="genre" required><br><br>
        <input type="submit" value="提交">
        <input type="button" value="返回" onclick="history.back()">
    </form>
</body>
</html>
