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
public class RequestUserDto {
    private String email;
    private Date birthDate;
    private String firstName;
    private String middleName;
    private String secondName;
    private String password;
}
