package szj.study.jsp.servlet.studio;

import szj.study.jsp.pojo.User;
import szj.study.jsp.service.IStudioService;
import szj.study.jsp.service.impl.StudioServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/deleteStudio")
public class DeleteStudioServlet extends HttpServlet {
    private final IStudioService studioService = new StudioServiceImpl();

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
            int studioId = Integer.parseInt(req.getParameter("studioId"));
            studioService.deleteStudio(studioId);
            resp.sendRedirect("allStudios");
            
        } catch (IllegalAccessException e) {
            req.getRequestDispatcher("/permissionDenied.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println("删除工作室失败: " + e.getMessage());
            resp.sendRedirect("allStudios");
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