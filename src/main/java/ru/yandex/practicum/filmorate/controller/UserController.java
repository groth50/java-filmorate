package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class UserController {
    Map<Long, User> users = new ConcurrentHashMap();

    public User addUser(@Valid @RequestBody User user) {
        return users.put(user.getId(), user);
    }

    public User updateUser(@Valid @RequestBody User user) {
        return users.put(user.getId(), user);
    }

    public Collection<User> getAllUsers() {
        return users.values();
    }
}
