package web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import web.dao.UserDAO;
import web.model.User;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService extends UserDetailsService {
    void setUserDao(UserDAO userDAO);
    int saveUser();
    List<User> getAllUser();
    void createUser(User user);
    void deleteUser(int id);
    User getUserById(int id);
    void updateUser(User user);
}
