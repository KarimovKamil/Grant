package ru.itis.grant.validation.dto;

import ru.itis.grant.dto.request.RequestUserDto;
import ru.itis.grant.security.exception.IncorrectDataException;

import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDtoValidator {
    private static volatile UserDtoValidator instance;

    public static UserDtoValidator getInstance() {
        UserDtoValidator localInstance = instance;
        if (localInstance == null) {
            synchronized (UserDtoValidator.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new UserDtoValidator();
                }
            }
        }
        return localInstance;
    }

    public void verify(RequestUserDto userDto) {
        if (!verifyPassword(userDto.getPassword())) {
            throw new IncorrectDataException("password", "Неверно введен пароль");
        }
        if (!verifyEmail(userDto.getEmail())) {
            throw new IncorrectDataException("email", "Неверно введен email");
        }
        if (!verifyBirthDate(userDto.getBirthDate())) {
            throw new IncorrectDataException("birthDate", "Неверно введена дата рождения");
        }
    }

    public boolean verifyEmail(String email) {
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return !Objects.isNull(email) && email.length() != 0 && matcher.matches();
    }

    public boolean verifyPassword(String password) {
        return !Objects.isNull(password) && password.length() >= 6 && password.length() <= 40;
    }

    public boolean verifyBirthDate(Date date) {
        return !Objects.isNull(date) && !date.after(new Date(System.currentTimeMillis()));
    }
}
