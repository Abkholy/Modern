package modern.io.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;

@Entity
public class timeTable implements Serializable {
    private static final long serialVersionUID = 1485758037652476045L;

    @Id
    private String id;
    private String code;
    private DayOfWeek dayOfWeek;
    @Column(length = 2)
    private int length;
    @Column(length = 2)
    private int period;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subjectDAO", referencedColumnName = "id")
    private subject subject;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location", referencedColumnName = "id")
    private location location;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "semester", referencedColumnName = "id")
    private semester semester;


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

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }


    public subject getSubject() {
        return subject;
    }

    public void setSubject(subject subject) {
        this.subject = subject;
    }

    public location getLocation() {
        return location;
    }

    public void setLocation(location location) {
        this.location = location;
    }


    public modern.io.entity.semester getSemester() {
        return semester;
    }

    public void setSemester(modern.io.entity.semester semester) {
        this.semester = semester;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
