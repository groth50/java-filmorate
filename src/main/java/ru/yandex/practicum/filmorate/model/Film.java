package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.time.Instant;

@Data
public class Film {
    private long id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @Length(max = 200)
    private String description;
    @NotNull
    private Instant releaseDate;
    @Positive
    private int duration;
}
