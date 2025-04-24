package ru.vsurin.task3nau.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер отображения страницы успешного входа
 */
@Controller
public class LoginSuccessController {

    /**
     * Возвращает страницу успешного входа
     */
    @GetMapping("/login-success")
    public String registrationForm() {
        return "loginSuccessPage";
    }
}
