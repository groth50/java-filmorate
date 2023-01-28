package ru.yandex.practicum.filmorate.controller;

import lombok.NonNull;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.validate.ValidateConfig;
import ru.yandex.practicum.filmorate.validate.ValidationException;

import javax.validation.Valid;
import java.time.Instant;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@RestController
public class FilmController {
    Map<Long, Film> films = new ConcurrentHashMap<>();
//    добавление фильма
    public Film addFilm(@Valid @RequestBody Film film) {
        validate(film);
        return films.put(film.getId(), film);
    }

    //    обновление фильма;
    public Film updateFilm(@Valid @RequestBody Film film) {
        validate(film);
        return films.put(film.getId(), film);
    }
//    получение всех фильмов.
    public Collection<Film> getAllFilms() {
        return films.values();
    }

    private static void validate(Film film) {
        if (film.getReleaseDate().isBefore(ValidateConfig.RELEASE_DATE_CONSTRAINT)) {
            throw new ValidationException();
        }
    }
}
