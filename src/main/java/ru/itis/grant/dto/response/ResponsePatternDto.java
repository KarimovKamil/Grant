package ru.itis.grant.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponsePatternDto {
    private long id;
    private String applicationName;
    private ResponseEventDto event;
    private List<ResponseElementDto> elements;
}
