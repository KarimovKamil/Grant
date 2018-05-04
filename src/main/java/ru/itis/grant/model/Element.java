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
    @ManyToOne(fetch = FetchType.LAZY)
    private Pattern pattern;
}