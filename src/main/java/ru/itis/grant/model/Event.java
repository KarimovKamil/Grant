package ru.itis.grant.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "g_event")
public class Event {
    @Id
    @GenericGenerator(name="ev_id" , strategy="increment")
    @GeneratedValue(generator="ev_id")
    private long id;
    private String name;
    @Column(name = "site_url")
    private String siteUrl;
    @ManyToOne
    private User owner;
    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Pattern pattern;

    public Event() {

    }

    public Event(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.siteUrl = builder.siteUrl;
        this.owner = builder.owner;
        this.pattern = builder.pattern;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public static class Builder {
        private long id;
        private String name;
        private String siteUrl;
        private User owner;
        private Pattern pattern;

        public Builder id(long arg) {
            this.id = arg;
            return this;
        }

        public Builder name(String arg) {
            this.name = arg;
            return this;
        }

        public Builder siteUrl(String arg) {
            this.siteUrl = arg;
            return this;
        }

        public Builder owner(User arg) {
            this.owner = arg;
            return this;
        }

        public Builder pattern(Pattern arg) {
            this.pattern = arg;
            return this;
        }

        public Event build() {
            return new Event(this);
        }
    }
}