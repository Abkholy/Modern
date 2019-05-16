package modern.io.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class semester {

    @Id
    private String id;
    private String code;
    private Date fromDate;
    private Date toDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "collage", referencedColumnName = "id")
    private collage collage;

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

    public modern.io.entity.collage getCollage() {
        return collage;
    }

    public void setCollage(modern.io.entity.collage collage) {
        this.collage = collage;
    }
}
