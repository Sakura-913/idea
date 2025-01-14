package szj.study.jsp.servlet.rating;

import szj.study.jsp.pojo.Rating;
import szj.study.jsp.service.IRatingService;
import szj.study.jsp.service.impl.RatingServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/allRatings")
public class AllRatingsServlet extends HttpServlet {
    private final IRatingService ratingService = new RatingServiceImpl();
    private static final int DEFAULT_PAGE_SIZE = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        
        try {
            // 获取分页和搜索参数
            String pageNumStr = req.getParameter("pageNum");
            String pageSizeStr = req.getParameter("pageSize");
            String searchKeyword = req.getParameter("searchKeyword");
            
            // 处理参数
            int pageNum = getIntParameter(pageNumStr, 1);
            int pageSize = getIntParameter(pageSizeStr, DEFAULT_PAGE_SIZE);
            
            if (searchKeyword != null) {
                searchKeyword = searchKeyword.trim();
                if (searchKeyword.isEmpty()) {
                    searchKeyword = null;
                }
            }

            // 获取数据
            int totalRecord = ratingService.getTotalRatings(searchKeyword);
            List<Rating> ratings = ratingService.getRatingsByPage(pageNum, pageSize, searchKeyword);

            // 计算总页数
            int totalPages = (totalRecord + pageSize - 1) / pageSize;
            
            // 校正页码
            if (pageNum < 1) {
                pageNum = 1;
            } else if (pageNum > totalPages) {
                pageNum = totalPages;
            }

            // 设置属性
            req.setAttribute("ratings", ratings);
            req.setAttribute("currentPage", pageNum);
            req.setAttribute("totalPages", totalPages);

            // 转发到JSP
            req.getRequestDispatcher("/allRatings.jsp").forward(req, resp);
            
        } catch (Exception e) {
            System.out.println("获取评分列表失败: " + e.getMessage());
            resp.sendRedirect("allRatings?pageNum=1");
        }
    }

    private int getIntParameter(String value, int defaultValue) {
        if (value != null && !value.trim().isEmpty()) {
            try {
                int result = Integer.parseInt(value);
                return result > 0 ? result : defaultValue;
            } catch (NumberFormatException ignored) {
            }
        }
        return defaultValue;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
