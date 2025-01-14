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

@WebServlet("/updateRating")
public class UpdateRatingServlet extends HttpServlet {
    private final IRatingService ratingService = new RatingServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            // 直接获取评分信息并显示更新页面
            int ratingId = Integer.parseInt(req.getParameter("ratingId"));
            Rating rating = ratingService.getRatingById(ratingId);
            req.setAttribute("rating", rating);
            req.getRequestDispatcher("/updateRating.jsp").forward(req, resp);
        } catch (Exception e) {
            resp.sendRedirect("allRatings");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        
        try {
            // 获取并验证参数
            int ratingId = Integer.parseInt(req.getParameter("ratingId"));
            String movieName = req.getParameter("movieName");
            String studioName = req.getParameter("studioName");
            double ratingScore = Double.parseDouble(req.getParameter("ratingScore"));

            // 创建评分对象
            Rating rating = new Rating();
            rating.setRatingId(ratingId);
            rating.setMovieName(movieName);
            rating.setStudioName(studioName);
            rating.setRatingScore(ratingScore);
            rating.setRatingTime(new Date());

            // 更新评分
            ratingService.updateRating(rating);
            resp.sendRedirect("allRatings");
            
        } catch (Exception e) {
            resp.sendRedirect("allRatings");
        }
    }
}