package web.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import javax.annotation.PostConstruct;

@Component
public class DataInicializer {
    private final UserService userService;
    private final RoleService roleService;

    public DataInicializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void saveUser() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Role role1 = new Role("ADMIN");
        Role role2 = new Role("USER");
        roleService.createRole(role1);
        roleService.createRole(role2);
        User user1 = new User("ADMIN", "Иван", "Иванов", (byte) 29,
                "ivanov@mail.ru", encoder.encode("ADMIN"), role1, role2);
        userService.createUser(user1);
        User user2 = new User("USER", "Ирина", "Лапина", (byte) 35,
                "lapina@mail.ru", encoder.encode("USER"), role2);
        userService.createUser(user2);
    }
}
