package ru.itis.grant.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Bid {
    @Id
    @GenericGenerator(name = "bid_id", strategy = "increment")
    @GeneratedValue(generator = "bid_id")
    private long id;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "bid", cascade = CascadeType.ALL)
    private List<ElementValue> valueList;
    @ManyToOne
    private Pattern pattern;
    @DateTimeFormat
    @Column(name = "bid_date")
    private Date bidDate;
    private String status;
    private String comment;
}