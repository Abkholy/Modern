package modern.shared.dto;

import modern.io.entity.attTable;
import modern.io.entity.student;
import modern.io.entity.subject;
import modern.io.entity.timeTable;

import java.io.Serializable;
import java.util.Date;

public class attTableLinesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String code;
    private attTable attTable;
    private timeTable timeTable;
    private student student;
    private subject subject;
    private String scannerMacAddress;
    private Date date;
    private String hashedQR;
    private String readerMacAddress;
    private Date readingDateTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public modern.io.entity.student getStudent() {
        return student;
    }

    public void setStudent(modern.io.entity.student student) {
        this.student = student;
    }

    public String getHashedQR() {
        return hashedQR;
    }

    public void setHashedQR(String hashedQR) {
        this.hashedQR = hashedQR;
    }

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

    public student getStudentId() {
        return student;
    }

    public void setStudentId(student studentId) {
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

    public String getReaderMacAddress() {
        return readerMacAddress;
    }

    public void setReaderMacAddress(String readerMacAddress) {
        this.readerMacAddress = readerMacAddress;
    }

    public Date getReadingDateTime() {
        return readingDateTime;
    }

    public void setReadingDateTime(Date readingDateTime) {
        this.readingDateTime = readingDateTime;
    }
}
