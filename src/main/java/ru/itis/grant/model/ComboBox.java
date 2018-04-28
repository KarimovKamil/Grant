package ru.itis.grant.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("COMBOBOX")
public class ComboBox extends Element {
    private int[] selectableValue;

    public ComboBox() {
    }

    public ComboBox(Element.Builder builder) {
        super(builder);
    }

    public ComboBox(Builder builder) {
        super(builder);
        this.selectableValue = builder.selectableValue;
    }

    public int[] getSelectableValue() {
        return selectableValue;
    }

    public void setSelectableValue(int[] selectableValue) {
        this.selectableValue = selectableValue;
    }

    public static class Builder extends Element.Builder {
        private int[] selectableValue;

        public Builder selectableValue(int[] arg) {
            this.selectableValue = arg;
            return this;
        }

        public ComboBox build() {
            return new ComboBox(this);
        }
    }
}
