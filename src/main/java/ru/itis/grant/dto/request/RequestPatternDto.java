package ru.itis.grant.dto.request;

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
public class RequestPatternDto {
    private long eventId;
    private String bidName;
    private String description;
    private List<RequestElementDto> elements;
    private Date startDate;
    private Date endDate;
}
