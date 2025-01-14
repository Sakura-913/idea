package szj.study.jsp.dao;

import szj.study.jsp.pojo.Movie;
import java.util.List;

public interface IMovieDao {
    List<Movie> selectAllMovies();
    Movie selectMovieById(int movieId);
    int insertMovie(Movie movie);
    int updateMovie(Movie movie);
    int deleteMovie(int movieId);
    
    // 分页和搜索方法
    List<Movie> selectMoviesByPage(int pageNum, int pageSize, String searchKeyword);
    int getTotalMovies(String searchKeyword);
}
