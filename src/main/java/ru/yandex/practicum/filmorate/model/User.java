package ru.yandex.practicum.filmorate.model;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class User {
    private static long counterId = 0;
    private long id;
    @NotBlank
    @Email
    private String email;
    @NotBlank
//    @Pattern(regexp = "^\\s")
    private String login;
    private String name;
    @PastOrPresent
    private LocalDate birthday;
}
