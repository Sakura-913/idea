package szj.study.jsp.dao.impl;

import szj.study.jsp.dao.IRatingDao;
import szj.study.jsp.pojo.Rating;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatingDaoImpl implements IRatingDao {

    @Override
    public List<Rating> selectAllRatings() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sample?serverTimezone=Asia/Shanghai",
                    "root",
                    "root"
            );
            String sql = "select * from ratings order by rating_id asc";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            List<Rating> ratings = new ArrayList<>();
            while (rs.next()) {
                Rating rating = new Rating(
                    rs.getInt("rating_id"),
                    rs.getString("movie_name"),
                    rs.getString("studio_name"),
                    rs.getDouble("rating_score"),
                    rs.getTimestamp("rating_time")
                );
                ratings.add(rating);
            }
            return ratings;
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
    public Rating selectRatingById(int ratingId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sample?serverTimezone=Asia/Shanghai",
                    "root",
                    "root"
            );
            String sql = "select * from ratings where rating_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, ratingId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Rating rating = new Rating(
                    rs.getInt("rating_id"),
                    rs.getString("movie_name"),
                    rs.getString("studio_name"),
                    rs.getDouble("rating_score"),
                    rs.getTimestamp("rating_time")
                );
                return rating;
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
    public int insertRating(Rating rating) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sample?serverTimezone=Asia/Shanghai",
                    "root",
                    "root"
            );
            String sql = "insert into ratings (movie_name, studio_name, rating_score, rating_time) values (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, rating.getMovieName());
            pstmt.setString(2, rating.getStudioName());
            pstmt.setDouble(3, rating.getRatingScore());
            pstmt.setTimestamp(4, new Timestamp(rating.getRatingTime().getTime()));
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
    public int updateRating(Rating rating) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sample?serverTimezone=Asia/Shanghai",
                    "root",
                    "root"
            );
            String sql = "update ratings set movie_name = ?, studio_name = ?, rating_score = ?, rating_time = ? where rating_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, rating.getMovieName());
            pstmt.setString(2, rating.getStudioName());
            pstmt.setDouble(3, rating.getRatingScore());
            pstmt.setTimestamp(4, new Timestamp(rating.getRatingTime().getTime()));
            pstmt.setInt(5, rating.getRatingId());
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
    public int deleteRating(int ratingId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sample?serverTimezone=Asia/Shanghai",
                    "root",
                    "root"
            );
            String sql = "delete from ratings where rating_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, ratingId);
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
    public List<Rating> selectRatingsByPage(int pageNum, int pageSize, String searchKeyword) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sample?serverTimezone=Asia/Shanghai",
                    "root",
                    "root"
            );
            String sql;
            PreparedStatement pstmt;
            if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
                sql = "select * from ratings order by rating_time asc limit ?, ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, (pageNum - 1) * pageSize);
                pstmt.setInt(2, pageSize);
            } else {
                sql = "select * from ratings where movie_name like ? or studio_name like ? order by rating_time asc limit ?, ?";
                pstmt = conn.prepareStatement(sql);
                String likePattern = "%" + searchKeyword.trim() + "%";
                pstmt.setString(1, likePattern);
                pstmt.setString(2, likePattern);
                pstmt.setInt(3, (pageNum - 1) * pageSize);
                pstmt.setInt(4, pageSize);
            }
            
            ResultSet rs = pstmt.executeQuery();
            List<Rating> ratings = new ArrayList<>();
            while (rs.next()) {
                Rating rating = new Rating(
                    rs.getInt("rating_id"),
                    rs.getString("movie_name"),
                    rs.getString("studio_name"),
                    rs.getDouble("rating_score"),
                    rs.getTimestamp("rating_time")
                );
                ratings.add(rating);
            }
            return ratings;
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
    public int getTotalRatings(String searchKeyword) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sample?serverTimezone=Asia/Shanghai",
                    "root",
                    "root"
            );
            String sql;
            PreparedStatement pstmt;
            if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
                sql = "select count(*) from ratings";
                pstmt = conn.prepareStatement(sql);
            } else {
                sql = "select count(*) from ratings where movie_name like ? or studio_name like ?";
                pstmt = conn.prepareStatement(sql);
                String likePattern = "%" + searchKeyword.trim() + "%";
                pstmt.setString(1, likePattern);
                pstmt.setString(2, likePattern);
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
