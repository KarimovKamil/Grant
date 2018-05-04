package ru.itis.grant.conversion.request;

import ru.itis.grant.dto.request.RequestUserDto;
import ru.itis.grant.model.User;

public class RequestUserDtoToUserConverter {
    private static volatile RequestUserDtoToUserConverter instance;

    public static RequestUserDtoToUserConverter getInstance() {
        RequestUserDtoToUserConverter localInstance = instance;
        if (localInstance == null) {
            synchronized (RequestUserDtoToUserConverter.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new RequestUserDtoToUserConverter();
                }
            }
        }
        return localInstance;
    }

    public User convert(String token, String hashPassword, RequestUserDto requestUserDto) {
        return User.builder()
                .token(token)
                .hashPassword(hashPassword)
                .email(requestUserDto.getEmail())
                .birthDate(requestUserDto.getBirthDate())
                .firstName(requestUserDto.getFirstName())
                .middleName(requestUserDto.getMiddleName())
                .secondName(requestUserDto.getSecondName())
                .build();
    }
}