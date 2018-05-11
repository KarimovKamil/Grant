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
@Table(name = "element")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "selectable", discriminatorType = DiscriminatorType.STRING)
public class Element {
    @Id
    @GenericGenerator(name = "el_id", strategy = "increment")
    @GeneratedValue(generator = "el_id")
    private long id;
    private String name;
    private String type;
    private String description;
    @Column(name = "layout_x")
    private int layoutX;
    @Column(name = "layout_y")
    private int layoutY;
    @ManyToOne(fetch = FetchType.LAZY)
    private Pattern pattern;
    private boolean required;

    public Element(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.type = builder.type;
        this.description = builder.description;
        this.layoutX = builder.layoutX;
        this.layoutY = builder.layoutY;
        this.pattern = builder.pattern;
        this.required = builder.required;
    }

    public static class Builder {
        private long id;
        private String name;
        private String type;
        private String description;
        private int layoutX;
        private int layoutY;
        private Pattern pattern;
        private boolean required;

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

        public Builder required(boolean arg) {
            this.required = arg;
            return this;
        }

        public Element build() {
            return new Element(this);
        }
    }
}