package szj.study.jsp.dao;

import szj.study.jsp.pojo.User;
import java.util.List;

public interface IUserDao {
    User selectUserByUsername(String username);
    int insertUser(User user);

}
