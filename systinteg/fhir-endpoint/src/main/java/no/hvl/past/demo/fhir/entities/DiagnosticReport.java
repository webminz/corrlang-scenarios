package no.hvl.past.demo.fhir.entities;

import java.time.LocalDateTime;
import java.util.Collection;

public class DiagnosticReport {

    public enum DiagnosticReportStatus {
        REGISTERED,
        PARTIAL,
        PRELIMINARY,
        FINAL
    }

    private DiagnosticReportStatus status;

    private Patient subject;

    private LocalDateTime effectiveDateTime;

    private LocalDateTime issued;

    private Collection<Observation> result;

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

    public void setResult(Collection<Observation> result) {
        this.result = result;
    }

    public CodeableConcept getCoding() {
        return coding;
    }

    public void setCoding(CodeableConcept coding) {
        this.coding = coding;
    }
}
