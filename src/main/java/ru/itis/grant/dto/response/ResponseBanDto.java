package ru.itis.grant.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseBanDto {
    private long id;
    private ResponseUserDto user;
    private ResponseEventDto event;
    private String comment;
}
