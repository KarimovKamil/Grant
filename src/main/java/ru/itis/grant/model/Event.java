package ru.itis.grant.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "g_event")
public class Event {
    @Id
    @GenericGenerator(name = "ev_id", strategy = "increment")
    @GeneratedValue(generator = "ev_id")
    private long id;
    private String name;
    @Type(type = "text")
    private String description;
    @Column(name = "site_url")
    private String siteUrl;
    @ManyToOne
    private User owner;
    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    private Pattern pattern;
    @ManyToMany(mappedBy = "exEvents", cascade = CascadeType.ALL)
    private List<User> experts;
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Ban> bans;
}