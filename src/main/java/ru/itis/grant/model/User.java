package ru.itis.grant.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "g_user")
public class User {
    @Id
    @GenericGenerator(name="us_id" , strategy="increment")
    @GeneratedValue(generator="us_id")
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "second_name")
    private String secondName;
    @Column(name = "middle_name")
    private String middleName;
    private String token;
    @Column(name = "hash_password")
    private String hashPassword;
    @DateTimeFormat
    @Column(name = "birth_date")
    private Date birthDate;
    private String role;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Event> events;

    public User() {

    }

    public User(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.secondName = builder.secondName;
        this.middleName = builder.middleName;
        this.token = builder.token;
        this.hashPassword = builder.hashPassword;
        this.birthDate = builder.birthDate;
        this.role = builder.role;
        this.events = builder.events;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public static class Builder {
        private long id;
        private String firstName;
        private String secondName;
        private String middleName;
        private String token;
        private String hashPassword;
        private Date birthDate;
        private String role;
        private List<Event> events;

        public Builder id(long arg) {
            this.id = arg;
            return this;
        }

        public Builder firstName(String arg) {
            this.firstName = arg;
            return this;
        }

        public Builder secondName(String arg) {
            this.secondName = arg;
            return this;
        }

        public Builder middleName(String arg) {
            this.middleName = arg;
            return this;
        }

        public Builder token(String arg) {
            this.token = arg;
            return this;
        }

        public Builder hashPassword(String arg) {
            this.hashPassword = arg;
            return this;
        }

        public Builder birthDate(Date arg) {
            this.birthDate = arg;
            return this;
        }

        public Builder role(String arg) {
            this.role = arg;
            return this;
        }

        public Builder events(List<Event> arg) {
            this.events = arg;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}