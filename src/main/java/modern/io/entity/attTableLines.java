package modern.io.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class attTableLines implements Serializable {
    private static final long serialVersionUID = 4474939287142180552L;

    @Id
    private String id;
    private String code;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "attTable", referencedColumnName = "id")
    private attTable attTable;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "timeTable", referencedColumnName = "id")
    private timeTable timeTable;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student", referencedColumnName = "id")
    private student student;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subjectDAO", referencedColumnName = "id")
    private subject subject;
    private String scannerMacAddress;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

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

    public modern.io.entity.attTable getAttTable() {
        return attTable;
    }

    public void setAttTable(modern.io.entity.attTable attTable) {
        this.attTable = attTable;
    }

    public modern.io.entity.timeTable getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(modern.io.entity.timeTable timeTable) {
        this.timeTable = timeTable;
    }

    public modern.io.entity.student getStudent() {
        return student;
    }

    public void setStudent(modern.io.entity.student student) {
        this.student = student;
    }

    public modern.io.entity.subject getSubject() {
        return subject;
    }

    public void setSubject(modern.io.entity.subject subject) {
        this.subject = subject;
    }

    public String getScannerMacAddress() {
        return scannerMacAddress;
    }

    public void setScannerMacAddress(String scannerMacAddress) {
        this.scannerMacAddress = scannerMacAddress;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
