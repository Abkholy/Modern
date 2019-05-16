package modern.shared.dto;

import modern.io.entity.collage;

import java.io.Serializable;
import java.util.Date;

public class semesterDTO implements Serializable {
    private static final long serialVersionUID = -6231218326836199290L;

    private String id;
    private String code;
    private Date fromDate;
    private Date toDate;
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
