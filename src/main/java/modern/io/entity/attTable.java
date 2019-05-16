package modern.io.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

@Entity
public class attTable implements Serializable {
    private static final long serialVersionUID = 4568969787633699061L;

    @Id
    private String id;
    private String code;
    private int period;
    private int length;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(length = 255)
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject", referencedColumnName = "id")
    private subject subject;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location", referencedColumnName = "id")
    private location location;
    @JsonbTransient
    @OneToMany(mappedBy = "attTable", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private Collection<attTableLines> lines = new LinkedHashSet<>();

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
