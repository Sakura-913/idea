package szj.study.jsp.dao.impl;

import szj.study.jsp.dao.IStudioDao;
import szj.study.jsp.pojo.Studio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudioDaoImpl implements IStudioDao {

    @Override
    public List<Studio> selectAllStudios() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sample?serverTimezone=Asia/Shanghai",
                    "root",
                    "root"
            );
            String sql = "select * from studios order by studio_id asc";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            List<Studio> studios = new ArrayList<>();
            while (rs.next()) {
                Studio studio = new Studio(
                    rs.getInt("studio_id"),
                    rs.getString("studio_name"),
                    rs.getString("location"),
                    rs.getInt("found_year"),
                    rs.getString("description")
                );
                studios.add(studio);
            }
            return studios;
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
    public Studio selectStudioById(int studioId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sample?serverTimezone=Asia/Shanghai",
                    "root",
                    "root"
            );
            String sql = "select * from studios where studio_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studioId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Studio studio = new Studio(
                    rs.getInt("studio_id"),
                    rs.getString("studio_name"),
                    rs.getString("location"),
                    rs.getInt("found_year"),
                    rs.getString("description")
                );
                return studio;
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
    public int insertStudio(Studio studio) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sample?serverTimezone=Asia/Shanghai",
                    "root",
                    "root"
            );
            String sql = "insert into studios (studio_name, location, found_year, description) values (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, studio.getStudioName());
            pstmt.setString(2, studio.getLocation());
            pstmt.setInt(3, studio.getFoundYear());
            pstmt.setString(4, studio.getDescription());
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
    public int updateStudio(Studio studio) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sample?serverTimezone=Asia/Shanghai",
                    "root",
                    "root"
            );
            String sql = "update studios set studio_name = ?, location = ?, found_year = ?, description = ? where studio_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, studio.getStudioName());
            pstmt.setString(2, studio.getLocation());
            pstmt.setInt(3, studio.getFoundYear());
            pstmt.setString(4, studio.getDescription());
            pstmt.setInt(5, studio.getStudioId());
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
    public int deleteStudio(int studioId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sample?serverTimezone=Asia/Shanghai",
                    "root",
                    "root"
            );
            String sql = "delete from studios where studio_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, studioId);
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
    public List<Studio> selectStudiosByPage(int pageNum, int pageSize, String searchKeyword) {
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
                sql = "select * from studios order by studio_id asc limit ?, ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, (pageNum - 1) * pageSize);
                pstmt.setInt(2, pageSize);
            } else {
                sql = "select * from studios where studio_name like ? or location like ? order by studio_id asc limit ?, ?";
                pstmt = conn.prepareStatement(sql);
                String likePattern = "%" + searchKeyword.trim() + "%";
                pstmt.setString(1, likePattern);
                pstmt.setString(2, likePattern);
                pstmt.setInt(3, (pageNum - 1) * pageSize);
                pstmt.setInt(4, pageSize);
            }
            
            ResultSet rs = pstmt.executeQuery();
            List<Studio> studios = new ArrayList<>();
            while (rs.next()) {
                Studio studio = new Studio(
                    rs.getInt("studio_id"),
                    rs.getString("studio_name"),
                    rs.getString("location"),
                    rs.getInt("found_year"),
                    rs.getString("description")
                );
                studios.add(studio);
            }
            return studios;
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
    public int getTotalStudios(String searchKeyword) {
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
                sql = "select count(*) from studios";
                pstmt = conn.prepareStatement(sql);
            } else {
                sql = "select count(*) from studios where studio_name like ? or location like ? or description like ?";
                pstmt = conn.prepareStatement(sql);
                String likePattern = "%" + searchKeyword.trim() + "%";
                pstmt.setString(1, likePattern);
                pstmt.setString(2, likePattern);
                pstmt.setString(3, likePattern);
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