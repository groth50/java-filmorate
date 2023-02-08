package ru.yandex.practicum.filmorate.storage;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.validate.ValidationException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class InMemoryFilmStorage implements FilmStorage {
    private static AtomicLong filmId = new AtomicLong(0);

    private final Map<Long, Film> films = new ConcurrentHashMap<>();

    @Override
    public void addFilm(Film film) {
        if (film.getId() < 1) {
            film.setId(filmId.incrementAndGet());
        }
        films.put(film.getId(), film);
    }

    @Override
    public Film updateFilm(Film film) {
        if (!films.containsKey(film.getId())) {
            throw new ValidationException();
        }
        films.put(film.getId(), film);
        return film;
    }

    @Override
    public List<Film> getAllFilms() {
        return List.copyOf(films.values());
    }
}
