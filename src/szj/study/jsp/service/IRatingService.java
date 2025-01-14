package szj.study.jsp.service;

import szj.study.jsp.pojo.Rating;
import java.util.List;

public interface IRatingService {
    List<Rating> getAllRatings();
    Rating getRatingById(int ratingId);
    void addRating(Rating rating);
    void updateRating(Rating rating);
    void deleteRating(int ratingId);

    // 分页和搜索方法
    List<Rating> getRatingsByPage(int pageNum, int pageSize, String searchKeyword);
    int getTotalRatings(String searchKeyword);
}
