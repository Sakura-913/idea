package szj.study.jsp.dao.impl;

import szj.study.jsp.dao.IUserDao;
import szj.study.jsp.pojo.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements IUserDao {


    @Override
    public User selectUserByUsername(String username) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sample?serverTimeZone=Asia/Shanghai",
                    "root",
                    "root"
            );
            String sql = "select * from users where username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                return user;
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
    public int insertUser(User user) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sample?serverTimeZone=Asia/Shanghai",
                    "root",
                    "root"
            );
            String sql = "insert into users (username, password, role) values (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getRole());
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


}
