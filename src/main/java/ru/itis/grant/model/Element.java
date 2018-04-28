package ru.itis.grant.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "element")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class Element {
    @Id
    @GenericGenerator(name="el_id" , strategy="increment")
    @GeneratedValue(generator="el_id")
    private long id;
    private String name;
    @Column(insertable = false, updatable = false)
    private String type;
    private String description;
    @Column(name = "layout_x")
    private int layoutX;
    @Column(name = "layout_y")
    private int layoutY;
    @ManyToOne
    private Pattern pattern;

    public Element() {

    }

    public Element(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.type = builder.type;
        this.description = builder.description;
        this.layoutX = builder.layoutX;
        this.layoutY = builder.layoutY;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLayoutX() {
        return layoutX;
    }

    public void setLayoutX(int layoutX) {
        this.layoutX = layoutX;
    }

    public int getLayoutY() {
        return layoutY;
    }

    public void setLayoutY(int layoutY) {
        this.layoutY = layoutY;
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
        private String type;
        private String description;
        private int layoutX;
        private int layoutY;
        private Pattern pattern;

        public Builder id(long arg) {
            this.id = arg;
            return this;
        }

        public Builder name(String arg) {
            this.name = arg;
            return this;
        }

        public Builder type(String arg) {
            this.type = arg;
            return this;
        }

        public Builder description(String arg) {
            this.description = arg;
            return this;
        }

        public Builder layoutX(int arg) {
            this.layoutX = arg;
            return this;
        }

        public Builder layoutY(int arg) {
            this.layoutY = arg;
            return this;
        }

        public Builder pattern(Pattern arg) {
            this.pattern = arg;
            return this;
        }

        public Element build() {
            return new Element(this);
        }
    }
}