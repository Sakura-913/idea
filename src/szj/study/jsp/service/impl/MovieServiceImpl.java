package szj.study.jsp.service.impl;

import szj.study.jsp.dao.IMovieDao;
import szj.study.jsp.dao.impl.MovieDaoImpl;
import szj.study.jsp.pojo.Movie;
import szj.study.jsp.service.IMovieService;
import java.util.List;

public class MovieServiceImpl implements IMovieService {
    private final IMovieDao movieDao = new MovieDaoImpl();

    @Override
    public List<Movie> getAllMovies() {
        return movieDao.selectAllMovies();
    }

    @Override
    public Movie getMovieById(int movieId) {
        return movieDao.selectMovieById(movieId);
    }

    @Override
    public void addMovie(Movie movie) {
        movieDao.insertMovie(movie);
    }

    @Override
    public void updateMovie(Movie movie) {
        movieDao.updateMovie(movie);
    }

    @Override
    public void deleteMovie(int movieId) {
        movieDao.deleteMovie(movieId);
    }

    @Override
    public List<Movie> getMoviesByPage(int pageNum, int pageSize, String searchKeyword) {
        return movieDao.selectMoviesByPage(pageNum, pageSize, searchKeyword);
    }

    @Override
    public int getTotalMovies(String searchKeyword) {
        return movieDao.getTotalMovies(searchKeyword);
    }
}
