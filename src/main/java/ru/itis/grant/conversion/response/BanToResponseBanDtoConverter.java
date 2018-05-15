package ru.itis.grant.conversion.response;

import ru.itis.grant.dto.response.ResponseBanDto;
import ru.itis.grant.dto.response.ResponseEventDto;
import ru.itis.grant.dto.response.ResponseUserDto;
import ru.itis.grant.model.Ban;

public class BanToResponseBanDtoConverter {
    private static volatile BanToResponseBanDtoConverter instance;

    public static BanToResponseBanDtoConverter getInstance() {
        BanToResponseBanDtoConverter localInstance = instance;
        if (localInstance == null) {
            synchronized (BanToResponseBanDtoConverter.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new BanToResponseBanDtoConverter();
                }
            }
        }
        return localInstance;
    }

    public ResponseBanDto convert(Ban ban) {
        ResponseEventDto responseEventDto = EventToResponseEventDtoConverter.getInstance().convert(ban.getEvent());
        ResponseUserDto responseUserDto = UserToResponseUserDtoConverter.getInstance().convert(ban.getUser());
        return ResponseBanDto.builder()
                .id(ban.getId())
                .event(responseEventDto)
                .user(responseUserDto)
                .comment(ban.getComment())
                .build();
    }
}
