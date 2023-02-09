package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class InMemoryUserStorage implements UserStorage {
    private static AtomicLong userId = new AtomicLong(0);
    private final Map<Long, User> users = new ConcurrentHashMap<>();

    @Override
    public void addUser(User user) {
        if (user.getId() < 1) {
            user.setId(userId.incrementAndGet());
        }
        users.put(user.getId(), user);
    }

    @Override
    public User updateUser(User user) {
        if (!users.containsKey(user.getId())) {
            throw new UserNotFoundException(user.getId());
        }
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return List.copyOf(users.values());
    }

    @Override
    public User getUserById(Long id) {
        return users.get(id);
    }
}
