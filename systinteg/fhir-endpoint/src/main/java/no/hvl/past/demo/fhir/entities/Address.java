package no.hvl.past.demo.fhir.entities;

import no.hvl.past.demo.fhir.components.StringListConverter;

import javax.persistence.*;
import java.util.List;

@Entity
public class Address {

    public enum AddressUse {
        HOME,
        WORK,
        TEMP,
        OLD,
        BILLING
    }

    public enum AddressType {
        POSTAL,
        PHYSICAL,
        BOTH
    }

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private AddressUse use;

    @Enumerated(EnumType.STRING)
    private AddressType type;

    private String text;

    @Convert(converter = StringListConverter.class)
    private List<String> line;

    private String city;

    private String district;

    private String state;

    private String postalCode;

    private String country;

    public AddressUse getUse() {
        return use;
    }

    public void setUse(AddressUse use) {
        this.use = use;
    }

    public AddressType getType() {
        return type;
    }

    public void setType(AddressType type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getLine() {
        return line;
    }

    public void setLine(List<String> line) {
        this.line = line;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
