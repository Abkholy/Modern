package modern.shared.dto;

import modern.io.entity.location;
import modern.io.entity.semester;
import modern.io.entity.subject;

import java.io.Serializable;
import java.time.DayOfWeek;

public class timeTableDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String code;
    private DayOfWeek dayOfWeek;
    private int length;
    private int period;
    private subject subject;
    private location location;
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

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
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

    public semester getSemester() {
        return semester;
    }

    public void setSemester(semester semester) {
        this.semester = semester;
    }
}
