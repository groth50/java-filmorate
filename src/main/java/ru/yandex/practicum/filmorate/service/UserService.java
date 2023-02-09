package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserStorage userStorage;

    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public void addUser(User user) {
        userStorage.addUser(user);
    }

    public User updateUser(User user) {
        return userStorage.updateUser(user);
    }

    public List<User> getAllUsers() {
        return userStorage.getAllUsers();
    }

    public void addFriend(Long id, Long friendId) {
        User user = getUserById(id);
        User friend = getUserById(friendId);

        user.getFriends().add(friendId);
        friend.getFriends().add(id);
    }

    public void deleteFriend(Long id, Long friendId) {
        User user = getUserById(id);
        User friend = getUserById(friendId);

        user.getFriends().remove(friendId);
        friend.getFriends().remove(id);
    }

    public List<User> getCommonFriends(Long id, Long otherId) {
        User user = getUserById(id);
        User friend = getUserById(otherId);
        return user.getFriends().stream()
                .filter(userFriendsId -> friend.getFriends().contains(userFriendsId))
                .map(this::getUserById)
                .collect(Collectors.toList());
    }

    public User getUserById(Long id) {
        User user = userStorage.getUserById(id);
        if (user == null) {
            throw new UserNotFoundException(id);
        }
        return user;
    }

    public List<User> getFriends(Long id) {
        User user = getUserById(id);
        return user.getFriends().stream()
                .map(this::getUserById)
                .collect(Collectors.toList());
    }
}
