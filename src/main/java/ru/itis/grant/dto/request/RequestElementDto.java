package ru.itis.grant.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestElementDto {
    private String name;
    private String type;
    private String description;
    private int layoutX;
    private int layoutY;
    private String[] selectableValue;
}
