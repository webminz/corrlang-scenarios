package no.hvl.past.demo.bloodtests.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Quantity {

    private double value;

    private String unit;

    public Quantity() {
    }

    public Quantity(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }
}
