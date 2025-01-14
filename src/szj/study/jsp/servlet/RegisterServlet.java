package szj.study.jsp.servlet;

import szj.study.jsp.pojo.User;
import szj.study.jsp.service.IUserService;
import szj.study.jsp.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // 直接显示注册页面
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try {
            // 获取并验证参数
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String role = request.getParameter("role");

            if (username == null || username.trim().isEmpty() || 
                password == null || password.trim().isEmpty()) {
                throw new IllegalArgumentException("用户名和密码不能为空");
            }

            username = username.trim();
            
            // 检查用户名是否已存在
            if (userService.getUserByUsername(username) != null) {
                throw new IllegalArgumentException("用户名已存在");
            }

            // 创建新用户
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            // 设置用户角色，如果没有选择则默认为普通用户
            user.setRole(role != null && role.equals("admin") ? "admin" : "user");

            // 保存用户
            userService.addUser(user);
            response.sendRedirect("login.jsp");
            
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("注册失败: " + e.getMessage());
            request.setAttribute("error", "系统错误，请稍后重试");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}
