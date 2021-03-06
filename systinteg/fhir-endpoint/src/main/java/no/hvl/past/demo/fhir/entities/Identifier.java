package no.hvl.past.demo.fhir.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Identifier {

    private String value;

    private String system;

    @Enumerated(EnumType.STRING)
    @Column(name = "IDENTIFIER_USE")
    private IdentifierUse use;

    private String type;

    public Identifier() {
    }

    public Identifier(String value, String system) {
        this.value = value;
        this.system = system;
        this.use = IdentifierUse.USUAL;
    }

    public String getValue() {
        return value;
    }

    public String getSystem() {
        return system;
    }

    public IdentifierUse getUse() {
        return use;
    }

    public void setUse(IdentifierUse use) {
        this.use = use;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public enum IdentifierUse {
        USUAL,
        OFFICIAL,
        TEMP,
        SECONDARY,
        OLD
    }

    /**
     * https://www.hl7.org/fhir/v2/0203/index.html
     */
    public enum IdentiferType {
        DL,	/* Driver's license number	*/
        PPN, /*	Passport number */
        BRN,/* 	Breed Registry Number*/
        MR,/* 	Medical record number*/
        MCN,/* 	Microchip Number*/
        EN,/* 	Employer number*/
        TAX,/* 	Tax ID number*/
        NIIP,/* 	National Insurance Payor Identifier (Payor)*/
        PRN,/* 	Provider number*/
        MD,/* 	Medical License number*/
        DR,/* Donor Registration Number*/
        ACSN,/* 	Accession ID*/
        UDI,/* 	Universal Device Identifier*/
        SNO,/* 	Serial Number*/
        SB,/* 	Social Beneficiary Identifier*/
        PLAC,/* 	Placer Identifier*/
        FILL,/* 	Filler Identifier*/
        JHN	/* Jurisdictional health number (Canada)*/
    }


}
