<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="szj.study.jsp.pojo.Rating" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>更新评分</title>
</head>
<body>
    <h2>更新评分</h2>
    <a href="allRatings">返回列表</a>
    <hr>

    <%
    Rating rating = (Rating)request.getAttribute("rating");
    if(rating != null) {
    %>
        <form action="updateRating" method="post">
            <input type="hidden" name="ratingId" value="<%=rating.getRatingId()%>">
            电影名称：<input type="text" name="movieName" value="<%=rating.getMovieName()%>" ><br><br>
            工作室名称：<input type="text" name="studioName" value="<%=rating.getStudioName()%>" ><br><br>
            评分：<input type="number" name="ratingScore" value="<%=rating.getRatingScore()%>" 
                     min="0" max="10" step="0.1" ><br>
            <small>评分范围：0-10分，可精确到小数点后一位</small><br><br>
            <input type="submit" value="更新">
            <input type="button" value="返回" onclick="history.back()">
        </form>
    <%
    }
    %>
</body>
</html>
