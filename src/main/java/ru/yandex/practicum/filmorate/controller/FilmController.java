package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.validate.ValidateConfig;
import ru.yandex.practicum.filmorate.validate.ValidationException;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@RestController
@RequestMapping(path = "/films")
@Slf4j
public class FilmController {
    Map<Long, Film> films = new ConcurrentHashMap<>();

    @PostMapping
    public Film addFilm(@Valid @RequestBody Film film) {
        validate(film);
        log.info("add film");
        return films.put(film.getId(), film);
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
        validate(film);
        log.info("update film");
        return films.put(film.getId(), film);
    }

    @GetMapping
    public Collection<Film> getAllFilms() {
        return films.values();
    }

    private static void validate(Film film) {
        if (film.getReleaseDate().isBefore(ValidateConfig.RELEASE_DATE_CONSTRAINT)) {
            log.error("RELEASE_DATE_CONSTRAINT");
            throw new ValidationException("RELEASE_DATE_CONSTRAINT");
        }
    }
}
