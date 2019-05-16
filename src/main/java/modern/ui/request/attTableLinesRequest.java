package modern.ui.request;

import modern.io.entity.attTable;
import modern.io.entity.student;
import modern.io.entity.subject;
import modern.io.entity.timeTable;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class attTableLinesRequest {
    private String code;
    private attTable attTable;
    private timeTable timeTable;
    private student student;
    private subject subject;
    private String scannerMacAddress;
    private Date date;

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

    public student getStudent() {
        return student;
    }

    public void setStudent(student studentId) {
        this.student = studentId;
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
