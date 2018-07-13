package ru.itis.grant.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "el_value")
public class ElementValue {
    @Id
    @GenericGenerator(name = "elv_id", strategy = "increment")
    @GeneratedValue(generator = "elv_id")
    private long id;
    @ManyToOne
    private Bid bid;
    @ManyToOne
    private Element element;
    @Column(name = "filled_value")
    private String filledValue;

    @Override
    public String toString() {
        return "{" +
                element.toString() + ",\r\n" +
                "Значение: '" + filledValue + '\'' +
                '}';
    }
}
