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

    @OneToOne
    @JoinTable(name = "Leucocytes", joinColumns = @JoinColumn(name = "RESULT_ID"), inverseJoinColumns = @JoinColumn(name = "PROBE_ID"))
    private Result Leucocytes;

    @OneToOne
    @JoinTable(name = "Thrombocytes", joinColumns = @JoinColumn(name = "RESULT_ID"), inverseJoinColumns = @JoinColumn(name = "PROBE_ID"))
    private Result Thrombocytes;

    @OneToOne
    @JoinTable(name = "Glucose", joinColumns = @JoinColumn(name = "RESULT_ID"), inverseJoinColumns = @JoinColumn(name = "PROBE_ID"))
    private Result Glucose;

    @OneToOne
    @JoinTable(name = "HDLCholesterol", joinColumns = @JoinColumn(name = "RESULT_ID"), inverseJoinColumns = @JoinColumn(name = "PROBE_ID"))
    private Result HDLCholesterol;

    @OneToOne
    @JoinTable(name = "LDLCholesterol", joinColumns = @JoinColumn(name = "RESULT_ID"), inverseJoinColumns = @JoinColumn(name = "PROBE_ID"))
    private Result LDLCholesterol;

    @OneToOne
    @JoinTable(name = "Triglycerides", joinColumns = @JoinColumn(name = "RESULT_ID"), inverseJoinColumns = @JoinColumn(name = "PROBE_ID"))
    private Result Triglycerides;


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

    public Result getLeucocytes() {
        return Leucocytes;
    }

    public void setLeucocytes(Result leucocytes) {
        Leucocytes = leucocytes;
    }

    public Result getThrombocytes() {
        return Thrombocytes;
    }

    public void setThrombocytes(Result thrombocytes) {
        Thrombocytes = thrombocytes;
    }

    public Result getGlucose() {
        return Glucose;
    }

    public void setGlucose(Result glucose) {
        Glucose = glucose;
    }

    public Result getHDLCholesterol() {
        return HDLCholesterol;
    }

    public void setHDLCholesterol(Result HDLCholesterol) {
        this.HDLCholesterol = HDLCholesterol;
    }

    public Result getLDLCholesterol() {
        return LDLCholesterol;
    }

    public void setLDLCholesterol(Result LDLCholesterol) {
        this.LDLCholesterol = LDLCholesterol;
    }

    public Result getTriglycerides() {
        return Triglycerides;
    }

    public void setTriglycerides(Result triglycerides) {
        Triglycerides = triglycerides;
    }
}
