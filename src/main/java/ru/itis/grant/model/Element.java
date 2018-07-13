package ru.itis.grant.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Arrays;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "element")
public class Element {
    @Id
    @GenericGenerator(name = "el_id", strategy = "increment")
    @GeneratedValue(generator = "el_id")
    private long id;
    private String name;
    private String type;
    private String description;
    @Column(name = "layout_x")
    private int layoutX;
    @Column(name = "layout_y")
    private int layoutY;
    @ManyToOne
    private Pattern pattern;
    private boolean required;
    @Column(name = "selectable_value")
    private String[] selectableValue;

    @Override
    public String toString() {
        return "{" +
                "Тип: '" + type + '\'' +
                ", Выбираемые значения: " + Arrays.toString(selectableValue) +
                '}';
    }
}