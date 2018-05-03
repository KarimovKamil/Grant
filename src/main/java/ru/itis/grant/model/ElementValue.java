package ru.itis.grant.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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

    public ElementValue() {

    }

    public ElementValue(Builder builder) {
        this.id = builder.id;
        this.bid = builder.bid;
        this.element = builder.element;
        this.filledValue = builder.filledValue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Bid getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public String getFilledValue() {
        return filledValue;
    }

    public void setFilledValue(String filledValue) {
        this.filledValue = filledValue;
    }

    public static class Builder {
        private long id;
        private Bid bid;
        private Element element;
        private String filledValue;

        public Builder id(long arg) {
            this.id = arg;
            return this;
        }

        public Builder bid(Bid arg) {
            this.bid = arg;
            return this;
        }

        public Builder element(Element arg) {
            this.element = arg;
            return this;
        }

        public Builder filledValue(String arg) {
            this.filledValue = arg;
            return this;
        }


        public ElementValue build() {
            return new ElementValue(this);
        }
    }
}
