package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class AdminController {
    private UserService userService;
    private RoleService roleService;

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
        return "login";
    }

    @GetMapping(value = "users/add")
    public String editPage(Model model) {
        User user = new User();
        List<Role> roles = roleService.getAllRole();
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping(value = "users/add")
    public String addUser(@ModelAttribute("user") User user) {
        Set<Role> roles = new HashSet<>();
        ArrayList<String> roleUser = new ArrayList<>();
        roleUser = user.getRolesUser(user);
        for (String nameRole: roleUser) {
            roles.add(roleService.getRoleByName(nameRole));
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
        List<Role> roles = roleService.getAllRole();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "update";
    }

    @PostMapping(value = "users/update")
    public String update(@ModelAttribute("user") User user) {
        Set<Role> roles = new HashSet<>();
        ArrayList<String> roleUser = new ArrayList<>();
        roleUser = user.getRolesUser(user);
        for (String nameRole: roleUser) {
            roles.add(roleService.getRoleByName(nameRole));
        }
        user.setRoles(roles);
        userService.updateUser(user);
        return "redirect:/admin";
    }
}
