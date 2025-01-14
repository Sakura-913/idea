package szj.study.jsp.service;

import szj.study.jsp.pojo.Movie;
import java.util.List;

public interface IMovieService {
    List<Movie> getAllMovies();
    Movie getMovieById(int movieId);
    void addMovie(Movie movie);
    void updateMovie(Movie movie);
    void deleteMovie(int movieId);
    
    // 分页和搜索方法
    List<Movie> getMoviesByPage(int pageNum, int pageSize, String searchKeyword);
    int getTotalMovies(String searchKeyword);
}
