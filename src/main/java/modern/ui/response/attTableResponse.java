package modern.ui.response;

import modern.io.entity.attTableLines;
import modern.io.entity.location;
import modern.io.entity.subject;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

@XmlRootElement
public class attTableResponse {
    private String id;
    private String code;
    private Date date;
    private String description;
    private subject subject;
    private location location;
    private Collection<attTableLines> lines = new LinkedHashSet<>();
    private int period;
    private int length;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Collection<attTableLines> getLines() {
        return lines;
    }

    public void setLines(Collection<attTableLines> lines) {
        this.lines = lines;
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
}
