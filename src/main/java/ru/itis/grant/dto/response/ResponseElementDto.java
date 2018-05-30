package ru.itis.grant.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseElementDto {
    private long id;
    private String name;
    private String type;
    private String description;
    private int layoutX;
    private int layoutY;
    private boolean required;
    private String[] selectableValue;
}
