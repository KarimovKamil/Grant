package ru.itis.grant.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CHECKBOX")
public class Checkbox extends Element {

    public Checkbox() {
    }
}
