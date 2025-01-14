package szj.study.jsp.dao;

import szj.study.jsp.pojo.Studio;
import java.util.List;

public interface IStudioDao {
    List<Studio> selectAllStudios();
    Studio selectStudioById(int studioId);
    int insertStudio(Studio studio);
    int updateStudio(Studio studio);
    int deleteStudio(int studioId);
    
    // 分页和搜索方法
    List<Studio> selectStudiosByPage(int pageNum, int pageSize, String searchKeyword);
    int getTotalStudios(String searchKeyword);
} 