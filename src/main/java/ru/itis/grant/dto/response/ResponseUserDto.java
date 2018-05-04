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
public class ResponseUserDto {
    private long id;
    private String email;
    private Date birthDate;
    private String firstName;
    private String middleName;
    private String secondName;
    private String role;
}
