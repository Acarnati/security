package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class AdminController {
    private UserService userService;
    private int i = 0;

    public AdminController(UserService userService) {
        this.userService = userService;
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
    public String addUser(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value="users/delete")
    public String deleteUs(@RequestParam("id") int id) {
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
