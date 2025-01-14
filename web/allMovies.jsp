<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="szj.study.jsp.pojo.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>电影列表</title>
</head>
<body>
    <h2>电影列表</h2>
    <a href="main.jsp">返回主页</a>
    <hr>
    
    <!-- 搜索表单 -->
    <form action="allMovies" method="get">
        <input type="text" name="searchKeyword" placeholder="搜索..." 
               value="<%=request.getParameter("searchKeyword") != null ? request.getParameter("searchKeyword") : ""%>">
        <input type="submit" value="搜索">
    </form>
    <br>
..
    <!-- 电影列表表格 -->
    <table border="1">
        <tr>
            <th>ID</th>
            <th>名称</th>
            <th>导演</th>
            <th>演员</th>
            <th>年份</th>
            <th>类型</th>
            <th>操作</th>
        </tr>
        <%
        List<Movie> movies = (List<Movie>)request.getAttribute("movies");
        if(movies != null) {
            for(Movie movie : movies) {
        %>
            <tr>
                <td><%=movie.getMovieId()%></td>
                <td><%=movie.getMovieName()%></td>
                <td><%=movie.getDirector()%></td>
                <td><%=movie.getActors()%></td>
                <td><%=movie.getReleaseYear()%></td>
                <td><%=movie.getGenre()%></td>
                <td>
                    <%
                    // 获取当前用户
                    User currentUser = (User)session.getAttribute("user");
                    boolean isAdmin = currentUser != null && "admin".equals(currentUser.getRole());
                    %>
                    <% if(isAdmin) { %>
                        <a href="updateMovie?movieId=<%=movie.getMovieId()%>">更新</a>
                        <a href="deleteMovie?movieId=<%=movie.getMovieId()%>" 
                           onclick="return confirm('确定要删除这部电影吗？')">删除</a>
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
            //当前页数
        int currentPage = request.getAttribute("currentPage") != null ? 
                        (Integer)request.getAttribute("currentPage") : 1;
        //总页数
        int totalPages = request.getAttribute("totalPages") != null ? 
                       (Integer)request.getAttribute("totalPages") : 1;
        //搜索关键字
        String searchKeyword = request.getParameter("searchKeyword") != null ? 
                             request.getParameter("searchKeyword") : "";
        //页数大于一显示上一页
        if(currentPage > 1) {
        %>
            <a href="allMovies?pageNum=<%=currentPage-1%>&searchKeyword=<%=searchKeyword%>">上一页</a>
        <%
        }
        %>
        第<%=currentPage%>/<%=totalPages%>页
        <%
        if(currentPage < totalPages) {
        %>
            <a href="allMovies?pageNum=<%=currentPage+1%>&searchKeyword=<%=searchKeyword%>">下一页</a>
        <%
        }
        %>
    </div>

    <br>
    <%
    // 获取当前用户
    User currentUser = (User)session.getAttribute("user");
    boolean isAdmin = currentUser != null && "admin".equals(currentUser.getRole());
    %>
    <% if(isAdmin) { %>
        <a href="addMovie.jsp">添加电影</a>
    <% } %>
</body>
</html>