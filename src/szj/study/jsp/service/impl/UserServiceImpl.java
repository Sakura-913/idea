package szj.study.jsp.service.impl;

import szj.study.jsp.dao.IUserDao;
import szj.study.jsp.dao.impl.UserDaoImpl;
import szj.study.jsp.pojo.User;
import szj.study.jsp.service.IUserService;

public class UserServiceImpl implements IUserService {
    private final IUserDao userDao = new UserDaoImpl();

    @Override
    public User getUserByUsername(String username) {
        return userDao.selectUserByUsername(username);
    }

    @Override
    public void addUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public User login(String username, String password) {
        User user = getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
