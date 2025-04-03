package ru.vsurin.task3nau.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vsurin.task3nau.domain.User;
import ru.vsurin.task3nau.exception.UserDuplicateException;
import ru.vsurin.task3nau.service.UserService;

@Controller
public class RegistrationFormController {

    private final UserService userService;

    public RegistrationFormController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String adduser(User user, Model model) {
        try {
            userService.createUser(user);
            return "redirect:/login";
        }
        catch (UserDuplicateException e) {
            model.addAttribute("message", e.getMessage());
            return "registration";
        }
    }
}
