package no.hvl.past.demo.fhir.entities;

import javax.persistence.Embeddable;

@Embeddable
public class CodeableConcept {

    private String code;

    private String system;

    private String version;

    private String display;

    public CodeableConcept() {

    }

    public CodeableConcept(String code, String system) {
        this.code = code;
        this.system = system;
    }

    public String getCode() {
        return code;
    }

    public String getSystem() {
        return system;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
}
