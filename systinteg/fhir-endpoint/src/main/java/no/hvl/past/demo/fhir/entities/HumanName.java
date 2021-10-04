package no.hvl.past.demo.fhir.entities;

import no.hvl.past.demo.fhir.components.StringListConverter;

import javax.persistence.*;
import java.util.List;

@Embeddable
public class HumanName {

    public enum NameUse {
        USUAL,
        OFFICIAL,
        TEMP,
        NICKNAME,
        ANONYMOUS,
        OLD,
        MAIDEN
    }

    HumanName() {
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "NAME_USE")
    private NameUse use;

    private String text;

    private String family;

    @Convert(converter = StringListConverter.class)
    private List<String> given;

    @Convert(converter = StringListConverter.class)
    private List<String> prefix;

    @Convert(converter = StringListConverter.class)
    private List<String> suffix;

    public HumanName(NameUse use, String text, String family, List<String> given, List<String> prefix, List<String> suffix) {
        this.use = use;
        this.text = text;
        this.family = family;
        this.given = given;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public NameUse getUse() {
        return use;
    }

    public void setUse(NameUse use) {
        this.use = use;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public List<String> getGiven() {
        return given;
    }

    public void setGiven(List<String> given) {
        this.given = given;
    }

    public List<String> getPrefix() {
        return prefix;
    }

    public void setPrefix(List<String> prefix) {
        this.prefix = prefix;
    }

    public List<String> getSuffix() {
        return suffix;
    }

    public void setSuffix(List<String> suffix) {
        this.suffix = suffix;
    }
}
