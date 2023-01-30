package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.*;

import static org.hamcrest.MatcherAssert.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FilmControllerTest {

    private static final LocalDate BEFORE_RELEASE_DATE_CONSTRAINT = LocalDate.of(1895, 12, 27);

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void addFilmWhitEarlyReleaseDateShouldBeError() {
        Film film = getTestFilm();
        film.setReleaseDate(BEFORE_RELEASE_DATE_CONSTRAINT);

        ResponseEntity<Film> response = restTemplate.postForEntity("/films", film, Film.class);
        assertThat(response.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Test
    void updateFilmWhitEarlyReleaseDateShouldBeError() {
        Film film = getTestFilm();

        ResponseEntity<Film> create = restTemplate.postForEntity("/films", film, Film.class);
        film.setId(create.getBody().getId());
        film.setReleaseDate(BEFORE_RELEASE_DATE_CONSTRAINT);
        HttpEntity<Film> entity = new HttpEntity<Film>(film);
        ResponseEntity<Film> response = restTemplate.exchange("/films", HttpMethod.PUT, entity, Film.class);

        assertThat(response.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    private static Film getTestFilm() {
        Film film = new Film();
        film.setName("test");
        film.setDescription("test desc");
        film.setReleaseDate(LocalDate.of(1999, 12, 27));
        film.setDuration(100);
        return film;
    }
}