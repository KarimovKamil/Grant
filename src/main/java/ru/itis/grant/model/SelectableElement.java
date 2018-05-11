package ru.itis.grant.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("Selectable")
public class SelectableElement extends Element {
    private String[] selectableValue;

    public SelectableElement(Builder builder) {
        this.selectableValue = builder.selectableValue;
    }

    public static class Builder {
        private String[] selectableValue;

        public Builder selectableValue(String[] arg) {
            this.selectableValue = arg;
            return this;
        }

        public SelectableElement build() {
            return new SelectableElement(this);
        }
    }
}
