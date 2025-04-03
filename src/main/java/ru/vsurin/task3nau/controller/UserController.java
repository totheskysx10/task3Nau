package ru.vsurin.task3nau.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsurin.task3nau.domain.User;
import ru.vsurin.task3nau.exception.UserNotFoundException;
import ru.vsurin.task3nau.service.UserService;

/**
 * Контроллер для пользователей
 */
@RestController
@RequestMapping("/custom/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Получить пользователя по имени
     * @param name имя пользователя
     */
    @GetMapping("/get-by-name")
    public ResponseEntity<User> getByName(@RequestParam String name) throws UserNotFoundException {
        User user = userService.getUserByName(name);
        return ResponseEntity.ok(user);
    }

    /**
     * Удалить пользователя и все связанные сущности
     * @param name имя пользователя
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUserByName(@RequestParam String name) throws UserNotFoundException {
        userService.deleteUser(name);
        return ResponseEntity.ok().build();
    }
}
