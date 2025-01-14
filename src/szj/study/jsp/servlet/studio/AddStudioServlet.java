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

@WebServlet("/addStudio")
public class AddStudioServlet extends HttpServlet {
    private final IStudioService studioService = new StudioServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // 直接显示添加页面
        req.getRequestDispatcher("/addStudio.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        try {
            // 获取并验证参数
            String studioName = req.getParameter("studioName");
            String location = req.getParameter("location");
            String foundYearStr = req.getParameter("foundYear");

            if (studioName == null || studioName.trim().isEmpty() || 
                foundYearStr == null || foundYearStr.trim().isEmpty()) {
                throw new IllegalArgumentException("工作室名称和成立年份不能为空");
            }

            // 年份验证
            int foundYear = Integer.parseInt(foundYearStr);
            if (foundYear < 1900 || foundYear > 2100) {
                throw new IllegalArgumentException("成立年份必须在1900-2100之间");
            }

            Studio studio = new Studio();
            studio.setStudioName(studioName.trim());
            studio.setLocation(location);
            studio.setFoundYear(foundYear);
            studio.setDescription(req.getParameter("description"));

            studioService.addStudio(studio);
            resp.sendRedirect("allStudios");
            
        } catch (NumberFormatException e) {
            req.setAttribute("error", "成立年份必须是有效数字");
            req.getRequestDispatcher("/addStudio.jsp").forward(req, resp);
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/addStudio.jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println("添加工作室失败: " + e.getMessage());
            resp.sendRedirect("allStudios");
        }
    }
} 