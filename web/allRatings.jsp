<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="szj.study.jsp.pojo.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>评分列表</title>
</head>
<body>
    <h2>评分列表</h2>
    <a href="main.jsp">返回主页</a>
    <hr>
    
    <!-- 搜索表单 -->
    <form action="allRatings" method="get">
        <input type="text" name="searchKeyword" placeholder="搜索..." 
               value="<%=request.getParameter("searchKeyword") != null ? request.getParameter("searchKeyword") : ""%>">
        <input type="submit" value="搜索">
    </form>
    <br>

    <!-- 评分列表表格 -->
    <table border="1">
        <tr>
            <th>ID</th>
            <th>电影</th>
            <th>工作室</th>
            <th>评分</th>
            <th>时间</th>
            <th>操作</th>
        </tr>
        <%
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Rating> ratings = (List<Rating>)request.getAttribute("ratings");
        if(ratings != null) {
            for(Rating rating : ratings) {
        %>
            <tr>
                <td><%=rating.getRatingId()%></td>
                <td><%=rating.getMovieName()%></td>
                <td><%=rating.getStudioName()%></td>
                <td><%=rating.getRatingScore()%></td>
                <td><%=sdf.format(rating.getRatingTime())%></td>
                <td>
                    <%
                    // 获取当前用户
                    User currentUser = (User)session.getAttribute("user");
                    boolean isAdmin = currentUser != null && "admin".equals(currentUser.getRole());
                    %>
                    <% if(isAdmin) { %>
                        <a href="updateRating?ratingId=<%=rating.getRatingId()%>">更新</a>
                        <a href="deleteRating?ratingId=<%=rating.getRatingId()%>" 
                           onclick="return confirm('确定要删除这条评分记录吗？')">删除</a>
                    <% } %>
                </td>
            </tr>
        <%
            }
        }
        %>
    </table>

    <!-- 分页导航 -->
    <div>
        <%
        int currentPage = request.getAttribute("currentPage") != null ? 
                        (Integer)request.getAttribute("currentPage") : 1;
        int totalPages = request.getAttribute("totalPages") != null ? 
                       (Integer)request.getAttribute("totalPages") : 1;
        String searchKeyword = request.getParameter("searchKeyword") != null ? 
                             request.getParameter("searchKeyword") : "";
        
        if(currentPage > 1) {
        %>
            <a href="allRatings?pageNum=<%=currentPage-1%>&searchKeyword=<%=searchKeyword%>">上一页</a>
        <%
        }
        %>
        第<%=currentPage%>/<%=totalPages%>页
        <%
        if(currentPage < totalPages) {
        %>
            <a href="allRatings?pageNum=<%=currentPage+1%>&searchKeyword=<%=searchKeyword%>">下一页</a>
        <%
        }
        %>
    </div>
    <%
        // 获取当前用户
        User currentUser = (User)session.getAttribute("user");
        boolean isAdmin = currentUser != null && "admin".equals(currentUser.getRole());
    %>
    <% if(isAdmin) { %>
    <a href="addRating.jsp">添加评分</a>
    <% } %>
</body>
</html>
