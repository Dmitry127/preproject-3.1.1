package ru.dmitry.seleznev.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dmitry.seleznev.model.User;
import ru.dmitry.seleznev.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("user") User user, @RequestParam(defaultValue = "") String adminRole) {
        user.setRoles(userService.getRoleSet(adminRole));
        userService.saveUser(user);
        return "redirect:/admin";
    }


    @GetMapping("/update")
    public String updatePage(Model model, @RequestParam("login") String login) {
        model.addAttribute("user", userService.getUser(login));
        return "update";
    }


    @PatchMapping()
    public String updateUser(@ModelAttribute("user") User user, @RequestParam(defaultValue = "") String adminRole) {
        User persistentUser = userService.getUser(user.getLogin());
        persistentUser.setPassword(user.getPassword());
        persistentUser.setName(user.getName());
        persistentUser.setLastName(user.getLastName());
        persistentUser.setEmail(user.getEmail());
        persistentUser.setRoles(userService.getRoleSet(adminRole));
        userService.updateUser(persistentUser);
        return "redirect:/admin";
    }

    @DeleteMapping()
    public String deleteUser(@RequestParam("login") String login) {
        userService.deleteUser(login);
        return "redirect:/admin";
    }

    @ModelAttribute
    public void addAttribute(Model model) {
        model.addAttribute("user", new User());
    }
}
