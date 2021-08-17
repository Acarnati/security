package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/")
public class AdminController {
    private UserService userService;
    private RoleService roleService;
    private int i = 0;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/admin")
    public String printWelcome(ModelMap model) {
        model.addAttribute("users", userService.getAllUser());
        return "admin";
    }

    @GetMapping(value = "/userPage")
    public String printWelcomeUser(ModelMap model, Principal principal) {
        model.addAttribute("users", userService.loadUserByUsername(principal.getName()));
        return "userPage";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        if (i != 1) {
            i = userService.saveUser();
        }
        return "login";
    }

    @GetMapping(value = "users/add")
    public String editPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping(value = "users/add")
    public String addUser(@ModelAttribute("user") User user, @RequestParam(required=false) String roleAdmin,
                          @RequestParam(required=false) String roleUser) {
        Set<Role> roles = new HashSet<>();
        if (roleAdmin != null) {
            roles.add(roleService.getRoleByName("ADMIN"));
        }
        if (roleUser != null) {
            roles.add(roleService.getRoleByName("USER"));
        }
        user.setRoles(roles);
        userService.createUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value="users/delete")
    public String deleteUs(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "users/update")
    public String updateUs(ModelMap model, @RequestParam("id") int id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "update";
    }

    @PostMapping(value = "users/update")
    public String update(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }
}
