package szj.study.jsp.service.impl;

import szj.study.jsp.dao.IRatingDao;
import szj.study.jsp.dao.impl.RatingDaoImpl;
import szj.study.jsp.pojo.Rating;
import szj.study.jsp.service.IRatingService;
import java.util.List;

public class RatingServiceImpl implements IRatingService {
    private final IRatingDao ratingDao = new RatingDaoImpl();

    @Override
    public List<Rating> getAllRatings() {
        return ratingDao.selectAllRatings();
    }

    @Override
    public Rating getRatingById(int ratingId) {
        return ratingDao.selectRatingById(ratingId);
    }

    @Override
    public void addRating(Rating rating) {
        ratingDao.insertRating(rating);
    }

    @Override
    public void updateRating(Rating rating) {
        ratingDao.updateRating(rating);
    }

    @Override
    public void deleteRating(int ratingId) {
        ratingDao.deleteRating(ratingId);
    }


    @Override
    public List<Rating> getRatingsByPage(int pageNum, int pageSize, String searchKeyword) {
        return ratingDao.selectRatingsByPage(pageNum, pageSize, searchKeyword);
    }

    @Override
    public int getTotalRatings(String searchKeyword) {
        return ratingDao.getTotalRatings(searchKeyword);
    }
}
