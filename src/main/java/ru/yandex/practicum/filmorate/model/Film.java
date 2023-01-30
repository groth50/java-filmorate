package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
public class Film {
    private long id;
    @NotBlank
    private String name;
    @NotNull
    @Length(max = 200)
    private String description;
    @NotNull
    private LocalDate releaseDate;
    @Positive
    private int duration;
}
