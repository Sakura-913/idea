package szj.study.jsp.service;

import szj.study.jsp.pojo.User;

public interface IUserService {
    User getUserByUsername(String username);
    void addUser(User user);
    User login(String username, String password);
}
