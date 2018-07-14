package ru.itis.grant.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseEventDto {
    private long id;
    private ResponseUserDto owner;
    private String name;
    private String description;
    private String siteUrl;
    private Date startDate;
    private Date endDate;
}
