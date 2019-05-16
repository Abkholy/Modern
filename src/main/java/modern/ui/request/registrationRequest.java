package modern.ui.request;

import modern.io.entity.registrationLines;
import modern.io.entity.semester;
import modern.io.entity.student;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

@XmlRootElement
public class registrationRequest {
    private String code;
    private String details;
    private String term;
    private int level;
    private Date fromDate;
    private Date toDate;
    private student student;
    private semester semester;

    private Collection<registrationLines> lines = new LinkedHashSet<>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public modern.io.entity.student getStudent() {
        return student;
    }

    public void setStudent(modern.io.entity.student student) {
        this.student = student;
    }

    public modern.io.entity.semester getSemester() {
        return semester;
    }

    public void setSemester(modern.io.entity.semester semester) {
        this.semester = semester;
    }

    public Collection<registrationLines> getLines() {
        return lines;
    }

    public void setLines(Collection<registrationLines> lines) {
        this.lines = lines;
    }
}
