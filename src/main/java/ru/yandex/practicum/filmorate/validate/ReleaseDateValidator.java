package ru.yandex.practicum.filmorate.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class ReleaseDateValidator  implements ConstraintValidator<ReleaseDateConstraint, LocalDate> {
    private static final LocalDate RELEASE_DATE_CONSTRAINT = LocalDate.of(1895, 12, 28);
    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
        return date.isAfter(RELEASE_DATE_CONSTRAINT);
    }
}
