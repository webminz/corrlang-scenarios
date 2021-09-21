package no.hvl.past.demo.fhir.entities;

import java.time.LocalDate;
import java.util.Collection;

public class Patient extends Resource {

    public enum AdministrativeGender {
        MALE,
        FEMALE,
        OTHER,
        UNKNWON
    }

    private Identifier identifier;

    private AdministrativeGender gender;

    private HumanName name;

    private LocalDate birthdate;

    private boolean CORRLANG_isStructurallyConsistent;

    private Collection<Address> contact;

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

    public boolean isCORRLANG_isStructurallyConsistent() {
        return CORRLANG_isStructurallyConsistent;
    }

    public void setCORRLANG_isStructurallyConsistent(boolean CORRLANG_isStructurallyConsistent) {
        this.CORRLANG_isStructurallyConsistent = CORRLANG_isStructurallyConsistent;
    }

    public Collection<Address> getContact() {
        return contact;
    }

    public void setContact(Collection<Address> contact) {
        this.contact = contact;
    }
}
