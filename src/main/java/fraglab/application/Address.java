package fraglab.application;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Address {

    @Id
    protected String id;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dateCreated;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date dateModified;

    private String streetName;

    private String streetNumber;

    private String postalCode;

    private String neighbourhood;

    private String city;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                '}';
    }

    @PrePersist()
    void prePersist() {
        dateCreated = new Date();
    }

    @PreUpdate()
    void preUpdate() {
        dateModified = new Date();
    }


}
