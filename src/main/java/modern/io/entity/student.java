package modern.io.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;

@Entity
public class student implements Serializable {
    private static final long serialVersionUID = 4209861311133078761L;

    @Id
    private String id;
    private String code;
    private String name;
    private String phoneNumber;
    private String ssn;
    private String email;
    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String image;
    private String level;
    private String macAddress;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "collage", referencedColumnName = "id")
    private collage collage;

    @JsonbTransient

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public modern.io.entity.collage getCollage() {
        return collage;
    }

    public void setCollage(modern.io.entity.collage collage) {
        this.collage = collage;
    }


}
