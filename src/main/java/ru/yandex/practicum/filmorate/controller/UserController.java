package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private static long userId = 1;
    Map<Long, User> users = new ConcurrentHashMap<>();

    @PostMapping
    public User addUser(@Valid @RequestBody User user) {
        validateAndChangeUserName(user);
        if (user.getId() < 1) {
            user.setId(userId);
            userId++;
        }
        log.info("add user {}", user);
        users.put(user.getId(), user);
        return user;
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user) {
        validateAndChangeUserName(user);
        log.info("update user {}", user);
        if (!users.containsKey(user.getId())) {
            throw new RuntimeException();
        }
        users.put(user.getId(), user);
        return user;
    }

    @GetMapping
    public Collection<User> getAllUsers() {
        return users.values();
    }

    private static void validateAndChangeUserName(User user) {
        String name = user.getName();
        if (name == null || name.isEmpty()) {
            user.setName(user.getLogin());
        }
    }
}
