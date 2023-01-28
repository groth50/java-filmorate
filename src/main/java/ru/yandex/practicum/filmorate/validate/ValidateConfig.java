package ru.yandex.practicum.filmorate.validate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.Temporal;

public class ValidateConfig {
    public static final Instant RELEASE_DATE_CONSTRAINT = Instant.from(LocalDate.of(1985, 12, 28));
}
