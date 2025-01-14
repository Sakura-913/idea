package szj.study.jsp.servlet.movie;

import szj.study.jsp.pojo.User;
import szj.study.jsp.service.IMovieService;
import szj.study.jsp.service.impl.MovieServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/deleteMovie")
public class DeleteMovieServlet extends HttpServlet {
    private final IMovieService movieService = new MovieServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        
        try {
            // 权限检查
            User user = getCurrentUser(req);
            if (user == null || !"admin".equals(user.getRole())) {
                throw new IllegalAccessException("权限不足");
            }

            // 获取并验证参数
            int movieId = Integer.parseInt(req.getParameter("movieId"));
            movieService.deleteMovie(movieId);
            resp.sendRedirect("allMovies");
            
        } catch (IllegalAccessException e) {
            req.getRequestDispatcher("/permissionDenied.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println("删除电影失败: " + e.getMessage());
            resp.sendRedirect("allMovies");
        }
    }

    private User getCurrentUser(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return session != null ? (User) session.getAttribute("user") : null;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        doGet(req, resp);
    }
}