package ru.itis.grant.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("RADIOBUTTON")
public class RadioButton extends Element {

    public RadioButton() {
    }
}
