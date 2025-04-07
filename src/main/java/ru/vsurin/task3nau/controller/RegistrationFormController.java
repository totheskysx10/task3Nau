package ru.vsurin.task3nau.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vsurin.task3nau.domain.User;
import ru.vsurin.task3nau.exception.UserDuplicateException;
import ru.vsurin.task3nau.response.RegisterDto;
import ru.vsurin.task3nau.service.UserService;

/**
 * Контроллер регистрации
 */
@Controller
public class RegistrationFormController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RegistrationFormController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Возвращает страницу регистрации
     */
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    /**
     * Регистрация пользователя
     * @param registerDto DTO регистрации
     * @param model модель
     */
    @PostMapping("/registration")
    public String adduser(RegisterDto registerDto, Model model) {
        try {
            User user = new User();
            user.setName(registerDto.getName());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

            userService.createUser(user);
            return "redirect:/login";
        }
        catch (UserDuplicateException e) {
            model.addAttribute("message", e.getMessage());
            return "registration";
        }
    }
}
