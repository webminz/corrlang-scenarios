package no.hvl.past.demo.fhir.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Encounter extends Resource {

    public enum  EncounterStatus {
        PLANNED,
        ARRIVED,
        TRIAGED,
        IN_PROGRESS,
        ONLEAVE,
        FINISHED,
        CANCELLED
    }

    @Enumerated(EnumType.STRING)
    private EncounterStatus status;

    private LocalDateTime start;

    private LocalDateTime end;

    @ManyToOne
    @JoinColumn(name = "PATIENT_ID")
    private Patient subject;

    public EncounterStatus getStatus() {
        return status;
    }

    public void setStatus(EncounterStatus status) {
        this.status = status;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Patient getSubject() {
        return subject;
    }

    public void setSubject(Patient subject) {
        this.subject = subject;
    }
}
