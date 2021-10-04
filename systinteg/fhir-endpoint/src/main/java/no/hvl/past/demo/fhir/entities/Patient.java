package no.hvl.past.demo.fhir.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Patient extends Resource {

    public enum AdministrativeGender {
        MALE,
        FEMALE,
        OTHER,
        UNKNWON
    }

    @Embedded
    private Identifier identifier;

    @Enumerated(EnumType.STRING)
    private AdministrativeGender gender;

    @Embedded
    private HumanName name;

    private LocalDate birthdate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PATIENT_ADDRESS", joinColumns = @JoinColumn(name = "PATIENT_ID"), inverseJoinColumns = @JoinColumn(name = "ADDRESS_ID"))
    private Set<Address> contact;

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public AdministrativeGender getGender() {
        return gender;
    }

    public void setGender(AdministrativeGender gender) {
        this.gender = gender;
    }

    public HumanName getName() {
        return name;
    }

    public void setName(HumanName name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Set<Address> getContact() {
        return contact;
    }

    public void setContact(Set<Address> contact) {
        this.contact = contact;
    }
}
