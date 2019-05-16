package modern.io.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;

@Entity
public class locationType implements Serializable {
    private static final long serialVersionUID = 405642569486653673L;
    @Id
    private String id;
    private String code;
    private String name;
    @JsonbTransient
    @OneToMany(mappedBy = "locationType", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private Collection<location> locations = new LinkedHashSet<>();

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<location> getLocations() {
        return locations;
    }

    public void setLocations(Collection<location> locations) {
        this.locations = locations;
    }

}
