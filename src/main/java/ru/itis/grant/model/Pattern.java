package ru.itis.grant.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Pattern {
    @Id
    @GenericGenerator(name="pt_id" , strategy="increment")
    @GeneratedValue(generator="pt_id")
    private long id;
    @Column(name = "bid_name")
    private String bidName;
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

    public Pattern() {
    }

    public Pattern(Builder builder) {
        this.id = builder.id;
        this.bidName = builder.bidName;
        this.event = builder.event;
        this.description = builder.description;
        this.endDate = builder.endDate;
        this.startDate = builder.startDate;
        this.elements = builder.elements;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBidName() {
        return bidName;
    }

    public void setBidName(String bidName) {
        this.bidName = bidName;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public static class Builder {
        private long id;
        private String bidName;
        private Event event;
        private String description;
        private Date endDate;
        private Date startDate;
        private List<Element> elements;

        public Builder id(long arg) {
            this.id = arg;
            return this;
        }

        public Builder bidName(String arg) {
            this.bidName = arg;
            return this;
        }

        public Builder event(Event arg) {
            this.event = arg;
            return this;
        }

        public Builder description(String arg) {
            this.description = arg;
            return this;
        }

        public Builder endDate(Date arg) {
            this.endDate = arg;
            return this;
        }

        public Builder startDate(Date arg) {
            this.startDate = arg;
            return this;
        }

        public Builder elements(List<Element> arg) {
            this.elements = arg;
            return this;
        }

        public Pattern build() {
            return new Pattern(this);
        }
    }
}