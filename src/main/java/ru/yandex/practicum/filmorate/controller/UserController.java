package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@Slf4j
public class UserController {
    Map<Long, User> users = new ConcurrentHashMap();

    public User addUser(@Valid @RequestBody User user) {
        validateAndChangeUserName(user);
        log.info("add user {}", user);
        return users.put(user.getId(), user);
    }

    public User updateUser(@Valid @RequestBody User user) {
        validateAndChangeUserName(user);
        log.info("update user {}", user);
        return users.put(user.getId(), user);
    }

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
