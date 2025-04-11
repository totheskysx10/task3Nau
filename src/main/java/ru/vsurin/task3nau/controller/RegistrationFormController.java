package ru.vsurin.task3nau.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер регистрации
 */
@Controller
public class RegistrationFormController {

    /**
     * Возвращает страницу регистрации
     */
    @GetMapping("/registration")
    public String registrationForm() {
        return "registration";
    }
}
