package no.hvl.past.demo.fhir.entities;


import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


public abstract class Resource {

    private String id;

    private String resourceType;

    private String htmlText;

    public Resource() {
    }

    public Resource(String id, String resourceType) {
        this.id = id;
        this.resourceType = resourceType;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getHtmlText() {
        return htmlText;
    }

    public void setHtmlText(String htmlText) {
        this.htmlText = htmlText;
    }
}

