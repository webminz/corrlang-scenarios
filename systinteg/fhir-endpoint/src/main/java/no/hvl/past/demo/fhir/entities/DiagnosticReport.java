package no.hvl.past.demo.fhir.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Entity
public class DiagnosticReport extends Resource {

    public enum DiagnosticReportStatus {
        REGISTERED,
        PARTIAL,
        PRELIMINARY,
        FINAL
    }

    @Enumerated(EnumType.STRING)
    private DiagnosticReportStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PATIENT_ID")
    private Patient subject;

    private LocalDateTime effectiveDateTime;

    private LocalDateTime issued;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ENCOUNTER_ID")
    private Encounter encounter;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "REPORT_OBSERVATIONS", joinColumns = @JoinColumn(name = "REPORT_ID"), inverseJoinColumns = @JoinColumn(name = "OBS_ID"))
    private Set<Observation> result;

    @Embedded
    private CodeableConcept coding;

    public DiagnosticReportStatus getStatus() {
        return status;
    }

    public void setStatus(DiagnosticReportStatus status) {
        this.status = status;
    }

    public Patient getSubject() {
        return subject;
    }

    public void setSubject(Patient subject) {
        this.subject = subject;
    }

    public LocalDateTime getEffectiveDateTime() {
        return effectiveDateTime;
    }

    public void setEffectiveDateTime(LocalDateTime effectiveDateTime) {
        this.effectiveDateTime = effectiveDateTime;
    }

    public LocalDateTime getIssued() {
        return issued;
    }

    public void setIssued(LocalDateTime issued) {
        this.issued = issued;
    }

    public Collection<Observation> getResult() {
        return result;
    }

    public void setResult(Set<Observation> result) {
        this.result = result;
    }

    public CodeableConcept getCoding() {
        return coding;
    }

    public void setCoding(CodeableConcept coding) {
        this.coding = coding;
    }

    public Encounter getEncounter() {
        return encounter;
    }

    public void setEncounter(Encounter encounter) {
        this.encounter = encounter;
    }
}
