package web.dao;

import web.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUser();
    User getUserByUsername(String username);
    void createUser(User user);
    void deleteUser(int id);
    User getUserById(int id);
    void updateUser(User user);
}
