package ru.yandex.practicum.filmorate.model;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.Instant;

@Data
public class User {
    private long id;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Pattern(regexp = "^\\s")
    private String login;
    private String name;
    @PastOrPresent
    private Instant birthday;
}
