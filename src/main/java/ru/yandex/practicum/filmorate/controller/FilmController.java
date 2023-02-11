package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.exception.ValidationException;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/films")
@Slf4j
@Validated
public class FilmController {
    private static final LocalDate RELEASE_DATE_CONSTRAINT = LocalDate.of(1895, 12, 28);
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping
    public Film addFilm(@Valid @RequestBody Film film) {
        validate(film);
        log.info("add film");
        filmService.addFilm(film);
        return film;
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
        validate(film);
        log.info("update film");
        return filmService.updateFilm(film);
    }

    @GetMapping
    public List<Film> getAllFilms() {
        return filmService.getAllFilms();
    }

    @GetMapping("/{id}")
    public Film getFilmById(@PathVariable Long id) {
        return filmService.getFilmById(id);
    }

    @PutMapping("/{id}/like/{userId}")
    public void addLike(@PathVariable Long id, @PathVariable Long userId) {
        filmService.addLike(id, userId);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public void deleteLike(@PathVariable Long id, @PathVariable Long userId) {
        filmService.deleteLike(id, userId);
    }

    @GetMapping("/popular")
    public List<Film> getTopFilms(@RequestParam(name = "count", defaultValue = "10")
                                  @Min(1)
                                  int count) {
        return filmService.getTopFilms(count);
    }

    private static void validate(Film film) {
        if (film.getReleaseDate().isBefore(RELEASE_DATE_CONSTRAINT)) {
            log.error("RELEASE_DATE_CONSTRAINT");
            throw new ValidationException("RELEASE_DATE_CONSTRAINT");
        }
    }
}
