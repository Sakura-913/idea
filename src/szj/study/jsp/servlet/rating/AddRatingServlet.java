package szj.study.jsp.servlet.rating;

import szj.study.jsp.pojo.Movie;
import szj.study.jsp.pojo.Rating;
import szj.study.jsp.pojo.Studio;
import szj.study.jsp.pojo.User;
import szj.study.jsp.service.IMovieService;
import szj.study.jsp.service.IRatingService;
import szj.study.jsp.service.IStudioService;
import szj.study.jsp.service.impl.MovieServiceImpl;
import szj.study.jsp.service.impl.RatingServiceImpl;
import szj.study.jsp.service.impl.StudioServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/addRating")
public class AddRatingServlet extends HttpServlet {
    private final IRatingService ratingService = new RatingServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // 直接显示添加页面
        req.getRequestDispatcher("/addRating.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        try {
            // 权限检查
            User user = getCurrentUser(req);
            if (user == null) {
                resp.sendRedirect("login.jsp");
                return;
            }
            if (!"admin".equals(user.getRole())) {
                throw new IllegalAccessException("权限不足");
            }

            // 获取并验证参数
            String movieName = req.getParameter("movieName");
            String studioName = req.getParameter("studioName");
            String scoreStr = req.getParameter("ratingScore");

            if (movieName == null || movieName.trim().isEmpty() || 
                studioName == null || studioName.trim().isEmpty() ||
                scoreStr == null || scoreStr.trim().isEmpty()) {
                throw new IllegalArgumentException("所有字段都不能为空");
            }

            double ratingScore = Double.parseDouble(scoreStr);
            if (ratingScore < 0 || ratingScore > 10) {
                throw new IllegalArgumentException("评分必须在0-10之间");
            }

            Rating rating = new Rating();
            rating.setMovieName(movieName.trim());
            rating.setStudioName(studioName.trim());
            rating.setRatingScore(ratingScore);
            rating.setRatingTime(new Date());

            ratingService.addRating(rating);
            resp.sendRedirect("allRatings");
            
        } catch (IllegalAccessException e) {
            req.getRequestDispatcher("/permissionDenied.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "评分必须是有效数字");
            req.getRequestDispatcher("/addRating.jsp").forward(req, resp);
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/addRating.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println("添加评分失败: " + e.getMessage());
            resp.sendRedirect("allRatings");
        }
    }

    private User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null ? (User) session.getAttribute("user") : null;
    }
}
