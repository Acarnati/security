package web.service;

import web.dao.UserDAO;
import web.model.User;

import java.util.List;

public interface UserService {
    void setUserDao(UserDAO userDAO);
    int saveUser();
    List<User> getAllUser();
}
