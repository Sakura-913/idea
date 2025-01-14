package szj.study.jsp.servlet;

import szj.study.jsp.pojo.User;
import szj.study.jsp.service.IUserService;
import szj.study.jsp.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 直接显示登录页面
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        try {
            // 获取并验证参数
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            if (username == null || username.trim().isEmpty() || 
                password == null || password.trim().isEmpty()) {
                throw new IllegalArgumentException("用户名和密码不能为空");
            }

            // 用户名和密码校验
            User user = userService.login(username.trim(), password);
            if (user == null) {
                throw new IllegalArgumentException("用户名或密码错误");
            }

            // 登录成功
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect("main.jsp");
            
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println("登录失败: " + e.getMessage());
            req.setAttribute("error", "系统错误，请稍后重试");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
