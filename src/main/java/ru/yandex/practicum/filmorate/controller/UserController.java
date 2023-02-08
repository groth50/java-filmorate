package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserStorage userStorage;

    public UserController(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @PostMapping
    public User addUser(@Valid @RequestBody User user) {
        validateAndChangeUserName(user);
        log.info("add user {}", user);
        userStorage.addUser(user);
        return user;
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user) {
        validateAndChangeUserName(user);
        log.info("update user {}", user);
        return userStorage.updateUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userStorage.getAllUsers();
    }

    private static void validateAndChangeUserName(User user) {
        String name = user.getName();
        if (name == null || name.isEmpty()) {
            user.setName(user.getLogin());
        }
    }
}
