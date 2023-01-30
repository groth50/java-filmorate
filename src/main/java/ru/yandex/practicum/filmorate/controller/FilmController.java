package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.validate.ValidationException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    private static long filmId = 1;
    private static final LocalDate RELEASE_DATE_CONSTRAINT = LocalDate.of(1895, 12, 28);
    Map<Long, Film> films = new ConcurrentHashMap<>();

    @PostMapping
    public Film addFilm(@Valid @RequestBody Film film) {
        validate(film);
        if (film.getId() < 1) {
            film.setId(filmId);
            filmId++;
        }
        log.info("add film");
        films.put(film.getId(), film);
        return film;
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
        validate(film);
        log.info("update film");
        if (!films.containsKey(film.getId())) {
            throw new ValidationException();
        }
        films.put(film.getId(), film);
        return film;
    }

    @GetMapping
    public List<Film> getAllFilms() {
        return List.copyOf(films.values());
    }

    private static void validate(Film film) {
        if (film.getReleaseDate().isBefore(RELEASE_DATE_CONSTRAINT)) {
            log.error("RELEASE_DATE_CONSTRAINT");
            throw new ValidationException("RELEASE_DATE_CONSTRAINT");
        }
    }
}
