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
@Table
public class Ban {
    @Id
    @GenericGenerator(name = "ban_id", strategy = "increment")
    @GeneratedValue(generator = "ban_id")
    private long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User expert;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Event event;
    private String comment;
}
