package modern.io.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class registrationLines implements Serializable {
    private static final long serialVersionUID = 5222417334695426778L;

    @Id
    private String id;
    private String code;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "registration", referencedColumnName = "id")
    private registration registration;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject", referencedColumnName = "id")
    private subject subject;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "timeTable", referencedColumnName = "id")
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

    public modern.io.entity.registration getRegistration() {
        return registration;
    }

    public void setRegistration(modern.io.entity.registration registration) {
        this.registration = registration;
    }

    public modern.io.entity.subject getSubject() {
        return subject;
    }

    public void setSubject(modern.io.entity.subject subject) {
        this.subject = subject;
    }

    public modern.io.entity.timeTable getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(modern.io.entity.timeTable timeTable) {
        this.timeTable = timeTable;
    }
}
