package no.hvl.past.demo.fhir.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Quantity {

    private Double value;

    private String unit;

    @Column(name = "QUANTITY_CODE_SYSTEM")
    private String system;

    @Column(name = "QUANTITY_CODE")
    private String code;

    public Quantity() {
    }

    public Quantity(Double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public Quantity(Double value, String unit, String system, String code) {
        this.value = value;
        this.unit = unit;
        this.system = system;
        this.code = code;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public String getSystem() {
        return system;
    }

    public String getCode() {
        return code;
    }
}
