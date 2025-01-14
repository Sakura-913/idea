<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="szj.study.jsp.pojo.Movie" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>更新电影</title>
</head>
<body>
    <h2>更新电影</h2>
    <a href="allMovies">返回列表</a>
    <hr>

    <%
    Movie movie = (Movie)request.getAttribute("movie");
    if(movie != null) {
    %>
        <form action="updateMovie" method="post">
            <input type="hidden" name="movieId" value="<%=movie.getMovieId()%>">
            电影名称：<input type="text" name="movieName" value="<%=movie.getMovieName()%>" ><br><br>
            导演：<input type="text" name="director" value="<%=movie.getDirector()%>" ><br><br>
            演员：<input type="text" name="actors" value="<%=movie.getActors()%>" ><br><br>
            上映年份：<input type="number" name="releaseYear" value="<%=movie.getReleaseYear()%>" ><br><br>
            类型：<input type="text" name="genre" value="<%=movie.getGenre()%>" ><br><br>
            <input type="submit" value="更新">
            <input type="button" value="返回" onclick="history.back()">
        </form>
    <%
    }
    %>
</body>
</html>
