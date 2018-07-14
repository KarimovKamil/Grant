package ru.itis.grant.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestEventDto {
    private String name;
    private String siteUrl;
    private String description;
    private Date startDate;
    private Date endDate;
}
