package ru.itis.grant.conversion.response;

import ru.itis.grant.dto.response.ResponseUserDto;
import ru.itis.grant.model.User;

public class UserToResponseUserDtoConverter {
    private static volatile UserToResponseUserDtoConverter instance;

    public static UserToResponseUserDtoConverter getInstance() {
        UserToResponseUserDtoConverter localInstance = instance;
        if (localInstance == null) {
            synchronized (UserToResponseUserDtoConverter.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new UserToResponseUserDtoConverter();
                }
            }
        }
        return localInstance;
    }

    public ResponseUserDto convert(User user) {
        return ResponseUserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .birthDate(user.getBirthDate())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .secondName(user.getSecondName())
                .role(user.getRole())
                .build();
    }
}