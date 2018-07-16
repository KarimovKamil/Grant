package ru.itis.grant.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Application {
    @Id
    @GenericGenerator(name = "application_id", strategy = "increment")
    @GeneratedValue(generator = "application_id")
    private long id;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL)
    private List<ElementValue> valueList;
    @ManyToOne
    private Pattern pattern;
    @DateTimeFormat
    @Column(name = "application_date")
    private Date applicationDate;
    private String status;
    private String comment;

    @Override
    public String toString() {
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        StringBuilder sb = new StringBuilder();
        sb.append("Заявка №").append(id).append(",\r\nОтправитель: ").append(user.toString())
                .append(",\r\nДата отправки: ").append(simpleDate.format(applicationDate)).append(",\r\n");
        for (ElementValue elementValue : valueList) {
            sb.append("Элемент: ").append(elementValue.toString()).append("\r\n");
        }
        return sb.toString();
    }
}
