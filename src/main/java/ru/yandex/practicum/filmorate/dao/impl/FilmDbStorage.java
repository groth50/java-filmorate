package ru.yandex.practicum.filmorate.dao.impl;

import ru.yandex.practicum.filmorate.dao.FilmStorage;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;
import java.util.Optional;

public class FilmDbStorage implements FilmStorage {
    @Override
    public void addFilm(Film film) {

    }

    @Override
    public Film updateFilm(Film film) {
        return null;
    }

    @Override
    public List<Film> getAllFilms() {
        return null;
    }

    @Override
    public Optional<Film> getFilmById(Long id) {
        return Optional.empty();
    }
}
