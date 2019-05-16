package modern.shared.dto;

import modern.io.entity.registration;
import modern.io.entity.subject;
import modern.io.entity.timeTable;

import java.io.Serializable;

public class registrationLinesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String code;
    private registration registration;
    private subject subject;
    private timeTable timeTable;

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

    public registration getRegistration() {
        return registration;
    }

    public void setRegistration(registration registration) {
        this.registration = registration;
    }

    public subject getSubject() {
        return subject;
    }

    public void setSubject(subject subject) {
        this.subject = subject;
    }

    public timeTable getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(timeTable timeTable) {
        this.timeTable = timeTable;
    }
}
