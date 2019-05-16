package modern.io.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class subject implements Serializable {
    private static final long serialVersionUID = 2344899839175374759L;

    @Id
    private String id;
    private String code;
    private String name;
    @Column(length = 5)
    private int creditHours;
    private String defaultInstructor;
    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String files;
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "collage", referencedColumnName = "id")
    private collage collage;

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

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public String getDefaultInstructor() {
        return defaultInstructor;
    }

    public void setDefaultInstructor(String defaultInstructor) {
        this.defaultInstructor = defaultInstructor;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public modern.io.entity.collage getCollage() {
        return collage;
    }

    public void setCollage(modern.io.entity.collage collage) {
        this.collage = collage;
    }
}
