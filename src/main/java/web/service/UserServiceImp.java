package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import web.dao.UserDAO;
import web.model.User;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {
    private UserDAO userDAO;

    @Autowired
    public void setUserDao(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public int saveUser() {
        return userDAO.saveUser();
    }

    @Override
    public List<User> getAllUser() {
        return userDAO.getAllUser();
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User myUser= userDAO.getUserByUsername(userName);
        if (myUser == null) {
            throw new UsernameNotFoundException("Unknown user: " + userName);
        }
        return new org.springframework.security.core.userdetails.User(myUser.getName(), myUser.getPassword(), myUser.getRoles());
    }
}
