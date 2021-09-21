package no.hvl.past.demo.fhir.entities;

import java.util.List;

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

    private AddressUse use;

    private AddressType type;

    private String text;

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
