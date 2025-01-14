<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="szj.study.jsp.pojo.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>工作室列表</title>
</head>
<body>
    <h2>工作室列表</h2>
    <a href="main.jsp">返回主页</a>
    <hr>
    
    <!-- 搜索表单 -->
    <form action="allStudios" method="get">
        <input type="text" name="searchKeyword" placeholder="搜索..." 
               value="<%=request.getParameter("searchKeyword") != null ? request.getParameter("searchKeyword") : ""%>">
        <input type="submit" value="搜索">
    </form>
    <br>

    <!-- 工作室列表表格 -->
    <table border="1">
        <tr>
            <th>ID</th>
            <th>名称</th>
            <th>地点</th>
            <th>成立年份</th>
            <th>描述</th>
            <th>操作</th>
        </tr>
        <%
        List<Studio> studios = (List<Studio>)request.getAttribute("studios");
        if(studios != null) {
            for(Studio studio : studios) {
        %>
            <tr>
                <td><%=studio.getStudioId()%></td>
                <td><%=studio.getStudioName()%></td>
                <td><%=studio.getLocation()%></td>
                <td><%=studio.getFoundYear()%></td>
                <td><%=studio.getDescription()%></td>
                <td>
                    <%
                    // 获取当前用户
                    User currentUser = (User)session.getAttribute("user");
                    boolean isAdmin = currentUser != null && "admin".equals(currentUser.getRole());
                    %>
                    <% if(isAdmin) { %>
                        <a href="updateStudio?studioId=<%=studio.getStudioId()%>">更新</a>
                        <a href="deleteStudio?studioId=<%=studio.getStudioId()%>" 
                           onclick="return confirm('确定要删除这个工作室吗？')">删除</a>
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
            <a href="allStudios?pageNum=<%=currentPage-1%>&searchKeyword=<%=searchKeyword%>">上一页</a>
        <%
        }
        %>
        第<%=currentPage%>/<%=totalPages%>页
        <%
        if(currentPage < totalPages) {
        %>
            <a href="allStudios?pageNum=<%=currentPage+1%>&searchKeyword=<%=searchKeyword%>">下一页</a>
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
    <a href="addStudio.jsp">添加工作室</a>
    <% } %>
</body>
</html> 