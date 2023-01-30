package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private static Validator validator;

    @BeforeAll
    static void beforeAll() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void testValidateWhitespaceInLoginShouldBeNot() {
        User user = getTestUser();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(0, violations.size());

        user.setLogin("with whitespace");
        Set<ConstraintViolation<User>> violationsWhitespace = validator.validate(user);
        assertEquals(1, violationsWhitespace.size());
    }


    @Test
    void addUserWhitEmptyNameShouldBeChangeNameOnLogin() {
        User user = getTestUser();
        user.setName("");

        ResponseEntity<User> response = restTemplate.postForEntity("/users", user, User.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().getName(), is(response.getBody().getLogin()));
    }

    @Test
    void updateUserWhitEmptyNameShouldBeChangeNameOnLogin() {
        User user = getTestUser();

        ResponseEntity<User> create = restTemplate.postForEntity("/users", user, User.class);

        user.setName("");
        user.setId(create.getBody().getId());

        HttpEntity<User> entity = new HttpEntity<>(user);
        ResponseEntity<User> response = restTemplate.exchange("/users", HttpMethod.PUT, entity, User.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().getName(), is(response.getBody().getLogin()));
    }

    private static User getTestUser() {
        User user = new User();
        user.setName("test");
        user.setLogin("test_login");
        user.setEmail("email@gmail.com");
        user.setBirthday(LocalDate.of(1984, 12, 12));
        return user;
    }

    @Test
    void updateUser() {
    }
}