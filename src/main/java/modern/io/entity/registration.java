package modern.io.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

@Entity
public class registration implements Serializable {
    private static final long serialVersionUID = 8795673222082581039L;

    @Id
    private String id;
    private String code;
    @Column(length = 255)
    private String details;
    private String term;
    private int level;
    private Date fromDate;
    private Date toDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student", referencedColumnName = "id")
    private student student;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "semester", referencedColumnName = "id")
    private semester semester;
    @JsonbTransient
    @OneToMany(mappedBy = "registration", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
