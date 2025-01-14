package szj.study.jsp.servlet.movie;

import szj.study.jsp.pojo.Movie;
import szj.study.jsp.service.IMovieService;
import szj.study.jsp.service.impl.MovieServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addMovie")
public class AddMovieServlet extends HttpServlet {
    private final IMovieService movieService = new MovieServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // 直接显示添加页面
        req.getRequestDispatcher("/addMovie.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        try {
            // 获取并验证参数
            String movieName = req.getParameter("movieName");
            String director = req.getParameter("director");
            String actors = req.getParameter("actors");
            String releaseYearStr = req.getParameter("releaseYear");
            String genre = req.getParameter("genre");
            String description = req.getParameter("description");

            // 参数验证
            if (movieName == null || movieName.trim().isEmpty() || 
                releaseYearStr == null || releaseYearStr.trim().isEmpty()) {
                throw new IllegalArgumentException("电影名称和发行年份不能为空");
            }

            // 年份验证
            int releaseYear = Integer.parseInt(releaseYearStr);
            if (releaseYear < 1900 || releaseYear > 2100) {
                throw new IllegalArgumentException("发行年份必须在1900-2100之间");
            }

            Movie movie = new Movie();
            movie.setMovieName(movieName.trim());
            movie.setDirector(director);
            movie.setActors(actors);
            movie.setReleaseYear(releaseYear);
            movie.setGenre(genre);
            movie.setDescription(description);

            movieService.addMovie(movie);
            resp.sendRedirect("allMovies");
            
        } catch (NumberFormatException e) {
            req.setAttribute("error", "发行年份必须是有效数字");
            req.getRequestDispatcher("/addMovie.jsp").forward(req, resp);
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/addMovie.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println("添加电影失败: " + e.getMessage());
            resp.sendRedirect("allMovies");
        }
    }
}