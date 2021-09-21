package no.hvl.past.demo.fhir.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Observation {

    private CodeableConcept coding;

    private LocalDateTime effectiveDateTime;

    private Quantity valueQuantity;


    public CodeableConcept getCoding() {
        return coding;
    }

    public void setCoding(CodeableConcept coding) {
        this.coding = coding;
    }

    public LocalDateTime getEffectiveDateTime() {
        return effectiveDateTime;
    }

    public void setEffectiveDateTime(LocalDateTime effectiveDateTime) {
        this.effectiveDateTime = effectiveDateTime;
    }

    public Quantity getValueQuantity() {
        return valueQuantity;
    }

    public void setValueQuantity(Quantity valueQuantity) {
        this.valueQuantity = valueQuantity;
    }
}
