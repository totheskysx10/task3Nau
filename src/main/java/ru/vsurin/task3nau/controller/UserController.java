package ru.vsurin.task3nau.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.vsurin.task3nau.assembler.UserAssembler;
import ru.vsurin.task3nau.domain.User;
import ru.vsurin.task3nau.exception.UserDuplicateException;
import ru.vsurin.task3nau.exception.UserNotFoundException;
import ru.vsurin.task3nau.response.RegisterDto;
import ru.vsurin.task3nau.response.UserDto;
import ru.vsurin.task3nau.service.UserService;

/**
 * Контроллер для пользователей
 */
@RestController
@RequestMapping("/custom/users")
public class UserController {

    private final UserService userService;
    private final UserAssembler userAssembler;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UserAssembler userAssembler, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userAssembler = userAssembler;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Регистрация пользователя
     * @param registerDto DTO регистрации
     */
    @PostMapping("/registration")
    public ResponseEntity<Void> addUser(@RequestBody RegisterDto registerDto) throws UserDuplicateException {
        User user = new User();
        user.setName(registerDto.getName());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        userService.createUser(user);
        return ResponseEntity.ok().build();
    }

    /**
     * Получить пользователя по имени
     * @param name имя пользователя
     */
    @GetMapping("/get-by-name")
    public ResponseEntity<UserDto> getByName(@RequestParam String name) throws UserNotFoundException {
        User user = userService.getUserByName(name);
        UserDto userDto = userAssembler.toDto(user);
        return ResponseEntity.ok(userDto);
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
