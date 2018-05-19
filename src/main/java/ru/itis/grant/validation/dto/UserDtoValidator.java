package ru.itis.grant.validation.dto;

import ru.itis.grant.dto.request.RequestUserDto;
import ru.itis.grant.security.exception.IncorrectDataException;

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

    public boolean verify(RequestUserDto userDto) {
        if (!verifyPassword(userDto.getPassword())) {
            return false;
        }

        return true;
    }

    public boolean verifyEmail(String email) {
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if (Objects.isNull(email) || email.length() == 0 || !matcher.matches()) {
            throw new IncorrectDataException("email", "Неверно введен email");
        }
        return true;
    }

    public boolean verifyPassword(String password) {
        if (Objects.isNull(password) || password.length() < 6 || password.length() > 40) {
            throw new IncorrectDataException("password", "Неверно введен пароль");
        }
        return true;
    }
}
