package szj.study.jsp.servlet.studio;

import szj.study.jsp.pojo.Studio;
import szj.study.jsp.service.IStudioService;
import szj.study.jsp.service.impl.StudioServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateStudio")
public class UpdateStudioServlet extends HttpServlet {
    private final IStudioService studioService = new StudioServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            // 直接获取工作室信息并显示更新页面
            int studioId = Integer.parseInt(req.getParameter("studioId"));
            Studio studio = studioService.getStudioById(studioId);
            req.setAttribute("studio", studio);
            req.getRequestDispatcher("/updateStudio.jsp").forward(req, resp);
        } catch (Exception e) {
            resp.sendRedirect("allStudios");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        
        try {
            // 获取并验证参数
            int studioId = Integer.parseInt(req.getParameter("studioId"));
            String studioName = req.getParameter("studioName");
            String location = req.getParameter("location");
            int foundYear = Integer.parseInt(req.getParameter("foundYear"));
            String description = req.getParameter("description");

            // 创建工作室对象
            Studio studio = new Studio(studioId, studioName, location, foundYear, description);

            // 更新工作室
            studioService.updateStudio(studio);
            resp.sendRedirect("allStudios");
            
        } catch (Exception e) {
            resp.sendRedirect("allStudios");
        }
    }
} 