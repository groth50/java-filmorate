package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.validate.ValidationException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    private static final LocalDate RELEASE_DATE_CONSTRAINT = LocalDate.of(1895, 12, 28);
    private final FilmStorage filmStorage;

    public FilmController(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    @PostMapping
    public Film addFilm(@Valid @RequestBody Film film) {
        validate(film);
        log.info("add film");
        filmStorage.addFilm(film);
        return film;
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
        validate(film);
        log.info("update film");
        return filmStorage.updateFilm(film);
    }

    @GetMapping
    public List<Film> getAllFilms() {
        return filmStorage.getAllFilms();
    }

    private static void validate(Film film) {
        if (film.getReleaseDate().isBefore(RELEASE_DATE_CONSTRAINT)) {
            log.error("RELEASE_DATE_CONSTRAINT");
            throw new ValidationException("RELEASE_DATE_CONSTRAINT");
        }
    }
}
