package lamph.auth.domain.valueobject;

import lamph.domain.common.exceptions.BusinessException;

import java.time.Duration;
import java.time.LocalDate;

public record BirthDate(LocalDate birthDate) {
    public static final int MIN_AGE = 16;

    public BirthDate {
        int minAgeDays = MIN_AGE * 365;
        if (birthDate == null || Duration.between(birthDate, LocalDate.now()).toDays() < minAgeDays) {
            throw new BusinessException("Birthdate is invalid");
        }
    }
}
