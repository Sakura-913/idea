package szj.study.jsp.servlet.movie;

import szj.study.jsp.pojo.Movie;
import szj.study.jsp.pojo.User;
import szj.study.jsp.service.IMovieService;
import szj.study.jsp.service.impl.MovieServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/updateMovie")
public class UpdateMovieServlet extends HttpServlet {
    private final IMovieService movieService = new MovieServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            // 直接获取电影信息并显示更新页面
            int movieId = Integer.parseInt(req.getParameter("movieId"));
            Movie movie = movieService.getMovieById(movieId);
            req.setAttribute("movie", movie);
            req.getRequestDispatcher("/updateMovie.jsp").forward(req, resp);
        } catch (Exception e) {
            resp.sendRedirect("allMovies");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 处理更新表单提交
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        
        try {
            // 获取并验证参数
            int movieId = Integer.parseInt(req.getParameter("movieId"));
            String movieName = req.getParameter("movieName");
            String director = req.getParameter("director");
            String actors = req.getParameter("actors");
            int releaseYear = Integer.parseInt(req.getParameter("releaseYear"));
            String genre = req.getParameter("genre");

            // 创建电影对象
            Movie movie = new Movie(movieId, movieName, director, actors, releaseYear, genre);

            // 更新电影
            movieService.updateMovie(movie);
            resp.sendRedirect("allMovies");
            
        } catch (Exception e) {
            resp.sendRedirect("allMovies");
        }
    }
}