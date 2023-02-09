package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.FilmNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService {

    private final FilmStorage filmStorage;
    private final UserService userService;

    public FilmService(FilmStorage filmStorage, UserService userService) {
        this.filmStorage = filmStorage;
        this.userService = userService;
    }


    public void addFilm(Film film) {
        filmStorage.addFilm(film);
    }

    public Film updateFilm(Film film) {
        long id = film.getId();
        Film filmById = filmStorage.getFilmById(id);
        if (filmById == null) {
            throw new FilmNotFoundException(id);
        }
        return filmStorage.updateFilm(film);
    }

    public List<Film> getAllFilms() {
        return filmStorage.getAllFilms();
    }

    public void addLike(Long filmId, Long userId) {
        Film film = this.getFilmById(filmId);
        User user = userService.getUserById(userId);
        film.getLikes().add(user.getId());
    }
    public void deleteLike(Long filmId, Long userId) {
        Film film = this.getFilmById(filmId);
        User user = userService.getUserById(userId);
        film.getLikes().remove(user.getId());
    }

//
    public List<Film> getTopFilms(int count) {
        return filmStorage.getAllFilms().stream()
                .sorted(Comparator.comparingInt( (Film film) -> film.getLikes().size()).reversed())
                .limit(count)
                .collect(Collectors.toList());
    }

    public Film getFilmById(Long id) {
        Film film = filmStorage.getFilmById(id);
        if (film == null) {
            throw new FilmNotFoundException(id);
        }
        return film;
    }
}
