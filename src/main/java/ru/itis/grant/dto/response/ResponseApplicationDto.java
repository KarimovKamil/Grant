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
public class ResponseApplicationDto {
    private long id;
    private ResponseUserDto user;
    private ResponsePatternDto pattern;
    private Date applicationDate;
    private String status;
    private List<ResponseElementValueDto> values;
    private String comment;
}
