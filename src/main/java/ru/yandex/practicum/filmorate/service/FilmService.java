package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FilmService {

    private final FilmStorage filmStorage;

    public FilmService(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }


    public void addFilm(Film film) {
        filmStorage.addFilm(film);
    }

    public Film updateFilm(Film film) {
        return filmStorage.updateFilm(film);
    }

    public List<Film> getAllFilms() {
        return filmStorage.getAllFilms();
    }

    public void addLike(User user, Film film) {
        film.getLikes().add(user.getId());
    }
    public void deleteLike(User user, Film film) {
        film.getLikes().remove(user.getId());
    }

    public List<Film> getTopFilms() {
        return filmStorage.getAllFilms().stream()
                .sorted(Comparator.comparingInt(film -> film.getLikes().size()))
                .collect(Collectors.toList());
    }
}