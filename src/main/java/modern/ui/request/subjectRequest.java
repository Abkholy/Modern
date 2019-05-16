package modern.ui.request;

import modern.io.entity.collage;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class subjectRequest {
    private String code;
    private String name;
    private int creditHours;
    private String defaultInstructor;
    private String files;
    private String description;
    private collage collage;

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
