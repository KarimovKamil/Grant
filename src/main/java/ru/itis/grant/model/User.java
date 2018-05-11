package ru.itis.grant.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "g_user")
public class User {
    @Id
    @GenericGenerator(name="us_id" , strategy="increment")
    @GeneratedValue(generator="us_id")
    private long id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "second_name")
    private String secondName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(unique = true)
    private String token;
    @Column(name = "hash_password", nullable = false)
    private String hashPassword;
    @DateTimeFormat
    @Column(name = "birth_date")
    private Date birthDate;
    private String role;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Event> events;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Event> exEvents;
}