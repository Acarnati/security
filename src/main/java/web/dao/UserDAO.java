package web.dao;

import web.model.User;

import java.util.List;

public interface UserDAO {
    int saveUser();
    List<User> getAllUser();
}
