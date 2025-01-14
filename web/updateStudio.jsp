<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="szj.study.jsp.pojo.Studio" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>更新工作室</title>
</head>
<body>
    <h2>更新工作室</h2>
    <a href="allStudios">返回列表</a>
    <hr>

    <%
    Studio studio = (Studio)request.getAttribute("studio");
    if(studio != null) {
    %>
        <form action="updateStudio" method="post">
            <input type="hidden" name="studioId" value="<%=studio.getStudioId()%>">
            工作室名称：<input type="text" name="studioName" value="<%=studio.getStudioName()%>" required><br><br>
            地点：<input type="text" name="location" value="<%=studio.getLocation()%>" required><br><br>
            成立年份：<input type="number" name="foundYear" value="<%=studio.getFoundYear()%>" required><br><br>
            描述：<br>
            <textarea name="description" rows="3" cols="50" required><%=studio.getDescription()%></textarea><br><br>
            <input type="submit" value="更新">
            <input type="button" value="返回" onclick="history.back()">
        </form>
    <%
    }
    %>
</body>
</html> 