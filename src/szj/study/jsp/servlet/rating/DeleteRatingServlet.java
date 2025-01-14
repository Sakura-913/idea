package szj.study.jsp.servlet.rating;

import szj.study.jsp.pojo.User;
import szj.study.jsp.service.IRatingService;
import szj.study.jsp.service.impl.RatingServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deleteRating")
public class DeleteRatingServlet extends HttpServlet {
    private final IRatingService ratingService = new RatingServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            // 权限检查
            User user = getCurrentUser(request);
            if (user == null || !"admin".equals(user.getRole())) {
                throw new IllegalAccessException("权限不足");
            }

            // 获取并验证参数
            int ratingId = Integer.parseInt(request.getParameter("ratingId"));
            ratingService.deleteRating(ratingId);
            response.sendRedirect("allRatings");
            
        } catch (IllegalAccessException e) {
            request.getRequestDispatcher("/permissionDenied.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("删除评分失败: " + e.getMessage());
            response.sendRedirect("allRatings");
        }
    }

    private User getCurrentUser(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return session != null ? (User) session.getAttribute("user") : null;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}