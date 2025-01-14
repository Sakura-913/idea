package szj.study.jsp.dao;

import szj.study.jsp.pojo.Rating;
import java.util.List;

public interface IRatingDao {
    List<Rating> selectAllRatings();
    Rating selectRatingById(int ratingId);
    int insertRating(Rating rating);
    int updateRating(Rating rating);
    int deleteRating(int ratingId);


    // 分页和搜索方法
    List<Rating> selectRatingsByPage(int pageNum, int pageSize, String searchKeyword);
    int getTotalRatings(String searchKeyword);

}
