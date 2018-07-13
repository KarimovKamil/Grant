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
public class Pattern {
    @Id
    @GenericGenerator(name = "pt_id", strategy = "increment")
    @GeneratedValue(generator = "pt_id")
    private long id;
    @Column(name = "application_name")
    private String applicationName;
    @OneToOne
    private Event event;
    private String description;
    @DateTimeFormat
    @Column(name = "end_date")
    private Date endDate;
    @DateTimeFormat
    @Column(name = "start_date")
    private Date startDate;
    @OneToMany(mappedBy = "pattern", cascade = CascadeType.ALL)
    private List<Element> elements;
    @OneToMany(mappedBy = "pattern", cascade = CascadeType.ALL)
    private List<Application> applications;
}