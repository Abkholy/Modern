package modern.ui.request;

import modern.io.entity.location;
import modern.io.entity.semester;
import modern.io.entity.subject;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.DayOfWeek;

@XmlRootElement
public class timeTableRequest {
    private String code;
    private DayOfWeek dayOfWeek;
    private int length;
    private int period;
    private subject subject;
    private location location;
    private semester semester;

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

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public modern.io.entity.subject getSubject() {
        return subject;
    }

    public void setSubject(modern.io.entity.subject subject) {
        this.subject = subject;
    }

    public modern.io.entity.location getLocation() {
        return location;
    }

    public void setLocation(modern.io.entity.location location) {
        this.location = location;
    }


    public modern.io.entity.semester getSemester() {
        return semester;
    }

    public void setSemester(modern.io.entity.semester semester) {
        this.semester = semester;
    }
}
