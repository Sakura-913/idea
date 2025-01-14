package szj.study.jsp.service;

import szj.study.jsp.pojo.Studio;
import java.util.List;

public interface IStudioService {
    List<Studio> getAllStudios();
    Studio getStudioById(int studioId);
    void addStudio(Studio studio);
    void updateStudio(Studio studio);
    void deleteStudio(int studioId);
    
    // 分页和搜索方法
    List<Studio> getStudiosByPage(int pageNum, int pageSize, String searchKeyword);
    int getTotalStudios(String searchKeyword);
} 