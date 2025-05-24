package pl.pcdevs.systemfinansowy.model;

import jakarta.persistence.Embeddable;

@Embeddable
public  class Adress {
    private String street;
    private String numberOfStreet;
    private String city;
    private String zip;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumberOfStreet() {
        return numberOfStreet;
    }

    public void setNumberOfStreet(String numberOfStreet) {
        this.numberOfStreet = numberOfStreet;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
