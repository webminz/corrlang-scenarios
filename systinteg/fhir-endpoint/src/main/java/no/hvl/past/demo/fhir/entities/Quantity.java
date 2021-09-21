package no.hvl.past.demo.fhir.entities;

public class Quantity {


    private final double value;

    private final String unit;

    private String system;

    private String code;

    public Quantity(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public Quantity(double value, String unit, String system, String code) {
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
