package ru.yandex.practicum.filmorate.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super(String.format("Пользователь ID = %d не найден", id));
    }
}
