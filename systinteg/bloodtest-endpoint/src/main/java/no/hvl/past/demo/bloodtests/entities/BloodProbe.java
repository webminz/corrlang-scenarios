package no.hvl.past.demo.bloodtests.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BloodProbe {

    @Id
    @GeneratedValue
    private Long id;

    private String patientId;

    private String physicianId;

    private LocalDateTime ordered;

    @OneToOne
    @JoinTable(name = "CRP", joinColumns = @JoinColumn(name = "RESULT_ID"), inverseJoinColumns = @JoinColumn(name = "PROBE_ID"))
    private Result CRP;

    @OneToOne
    @JoinTable(name = "Erythrocytes", joinColumns = @JoinColumn(name = "RESULT_ID"), inverseJoinColumns = @JoinColumn(name = "PROBE_ID"))
    private Result Erythrocytes;

    public BloodProbe() {
    }

    public BloodProbe(String patientId, String physicianId, LocalDateTime ordered) {
        this.patientId = patientId;
        this.physicianId = physicianId;
        this.ordered = ordered;
    }

    public Long getId() {
        return id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(String physicianId) {
        this.physicianId = physicianId;
    }

    public LocalDateTime getOrdered() {
        return ordered;
    }

    public void setOrdered(LocalDateTime ordered) {
        this.ordered = ordered;
    }

    public Result getCRP() {
        return CRP;
    }

    public void setCRP(Result CRP) {
        this.CRP = CRP;
    }

    public Result getErythrocytes() {
        return Erythrocytes;
    }

    public void setErythrocytes(Result erythrocytes) {
        Erythrocytes = erythrocytes;
    }
}
