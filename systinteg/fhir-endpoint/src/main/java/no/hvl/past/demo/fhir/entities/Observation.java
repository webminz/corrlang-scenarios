package no.hvl.past.demo.fhir.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Observation extends Resource {

    @Embedded
    private CodeableConcept coding;

    private LocalDateTime effectiveDateTime;

    @Embedded
    private Quantity valueQuantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PATIENT_ID")
    private Patient subject;

    public CodeableConcept getCoding() {
        return coding;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ENCOUNTER_ID")
    private Encounter encounter;

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

    public Patient getSubject() {
        return subject;
    }

    public void setSubject(Patient subject) {
        this.subject = subject;
    }

    public Encounter getEncounter() {
        return encounter;
    }

    public void setEncounter(Encounter encounter) {
        this.encounter = encounter;
    }
}
