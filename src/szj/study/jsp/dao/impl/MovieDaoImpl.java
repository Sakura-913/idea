package szj.study.jsp.dao.impl;

import szj.study.jsp.dao.IMovieDao;
import szj.study.jsp.pojo.Movie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDaoImpl implements IMovieDao {

    @Override
    public List<Movie> selectAllMovies() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sample?serverTimeZone=Asia/Shanghai",
                    "root",
                    "root"
            );
            String sql = "select * form movies order by movie_id asc ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            List<Movie> movies = new ArrayList<>();
            while (rs.next()) {
                Movie movie = new Movie(
                    rs.getInt("movie_id"),
                    rs.getString("movie_name"),
                    rs.getString("director"),
                    rs.getString("actors"),
                    rs.getInt("release_year"),
                    rs.getString("genre")
                );
                movies.add(movie);
            }
            return movies;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("数据库驱动错误！");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库访问错误！");
        }
        return null;
    }

    @Override
    public Movie selectMovieById(int movieId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sample?serverTimeZone=Asia/Shanghai",
                    "root",
                    "root"
            );
            String sql = "select * from movies where movie_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, movieId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Movie movie = new Movie(
                    rs.getInt("movie_id"),
                    rs.getString("movie_name"),
                    rs.getString("director"),
                    rs.getString("actors"),
                    rs.getInt("release_year"),
                    rs.getString("genre")
                );
                return movie;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("数据库驱动错误！");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库访问错误！");
        }
        return null;
    }

    @Override
    public int insertMovie(Movie movie) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sample?serverTimeZone=Asia/Shanghai",
                    "root",
                    "root"
            );
            String sql = "insert into movies (movie_name, director, actors, release_year, genre) values (?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, movie.getMovieName());
            pstmt.setString(2, movie.getDirector());
            pstmt.setString(3, movie.getActors());
            pstmt.setInt(4, movie.getReleaseYear());
            pstmt.setString(5, movie.getGenre());
            int result = pstmt.executeUpdate();
            return result;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("数据库驱动错误！");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库访问错误！");
        }
        return 0;
    }

    @Override
    public int updateMovie(Movie movie) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sample?serverTimeZone=Asia/Shanghai",
                    "root",
                    "root"
            );
            String sql = "update movies set movie_name=?, director=?, actors=?, release_year=?, genre=? where movie_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, movie.getMovieName());
            pstmt.setString(2, movie.getDirector());
            pstmt.setString(3, movie.getActors());
            pstmt.setInt(4, movie.getReleaseYear());
            pstmt.setString(5, movie.getGenre());
            pstmt.setInt(6, movie.getMovieId());
            int result = pstmt.executeUpdate();
            return result;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("数据库驱动错误！");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库访问错误！");
        }
        return 0;
    }

    @Override
    public int deleteMovie(int movieId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sample?serverTimeZone=Asia/Shanghai",
                    "root",
                    "root"
            );
            String sql = "delete from movies where movie_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, movieId);
            int result = pstmt.executeUpdate();
            return result;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("数据库驱动错误！");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库访问错误！");
        }
        return 0;
    }

    @Override
    public List<Movie> selectMoviesByPage(int pageNum, int pageSize, String searchKeyword) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sample?serverTimeZone=Asia/Shanghai",
                    "root",
                    "root"
            );
            String sql;
            PreparedStatement pstmt;
            if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
                sql = "select * from movies order by movie_id asc LIMIT ?, ?";
                pstmt = conn.prepareStatement(sql);//以便后续能更高效、安全地执行该 SQL 语句
                pstmt.setInt(1, (pageNum - 1) * pageSize);
                pstmt.setInt(2, pageSize);
            } else {
                sql = "select * from movies where movie_name like ? or director like ? or actors like ? or genre like ? order by movie_id asc LIMIT ?, ?";
                pstmt = conn.prepareStatement(sql);
                String likePattern = searchKeyword.trim() ;
                pstmt.setString(1, likePattern);
                pstmt.setString(2, likePattern);
                pstmt.setString(3, likePattern);
                pstmt.setString(4, likePattern);
                pstmt.setInt(5, (pageNum - 1) * pageSize);
                pstmt.setInt(6, pageSize);
            }
            
            ResultSet rs = pstmt.executeQuery();//执行查询语句
            List<Movie> movies = new ArrayList<>();
            while (rs.next()) {
                Movie movie = new Movie(
                    rs.getInt("movie_id"),
                    rs.getString("movie_name"),
                    rs.getString("director"),
                    rs.getString("actors"),
                    rs.getInt("release_year"),
                    rs.getString("genre")
                );
                movies.add(movie);
            }
            return movies;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("数据库驱动错误！");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库访问错误！");
        }
        return null;
    }

    @Override
    public int getTotalMovies(String searchKeyword) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sample?serverTimeZone=Asia/Shanghai",
                    "root",
                    "root"
            );
            String sql;
            PreparedStatement pstmt;
            if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
                sql = "select count(*) from movies";
                pstmt = conn.prepareStatement(sql);
            } else {
                sql = "select count(*) from movies where movie_name like ? or director like ? or actors like ? or genre like ?";
                pstmt = conn.prepareStatement(sql);
                String likePattern = "%" + searchKeyword.trim() + "%";
                pstmt.setString(1, likePattern);
                pstmt.setString(2, likePattern);
                pstmt.setString(3, likePattern);
                pstmt.setString(4, likePattern);
            }
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("数据库驱动错误！");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库访问错误！");
        }
        return 0;
    }
}
