package modern.ui.response;

import modern.io.entity.attTable;
import modern.io.entity.student;
import modern.io.entity.subject;
import modern.io.entity.timeTable;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class attTableLinesResponse {
    private String id;
    private String code;
    private attTable attTable;
    private timeTable timeTable;
    private student student;
    private subject subject;
    private String scannerMacAddress;
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
