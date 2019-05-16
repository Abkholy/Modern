package modern.io.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;

@Entity
public class collage implements Serializable {
    private static final long serialVersionUID = -5883689878174172546L;
    @Id
    private String id;
    private String code;
    private String name;
    private String location;
    private String phoneNumber;
    private String website;
    private String Email;
    @JsonbTransient
    @OneToMany(mappedBy = "collage", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private Collection<location> locations = new LinkedHashSet<>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Collection<modern.io.entity.location> getLocations() {
        return locations;
    }

    public void setLocations(Collection<modern.io.entity.location> locations) {
        this.locations = locations;
    }

}
