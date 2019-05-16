package modern.ui.request;

import modern.io.entity.registration;
import modern.io.entity.subject;
import modern.io.entity.timeTable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class registrationLinesRequest {
    private String code;
    private registration registration;
    private subject subject;
    private timeTable timeTable;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public registration getRegistration() {
        return registration;
    }

    public void setRegistration(registration registration) {
        this.registration = registration;
    }

    public subject getSubject() {
        return subject;
    }

    public void setSubject(subject subject) {
        this.subject = subject;
    }

    public timeTable getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(timeTable timeTable) {
        this.timeTable = timeTable;
    }
}
