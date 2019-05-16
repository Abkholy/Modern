package modern.shared.dto;


import modern.io.entity.location;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;

public class locationTypeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String code;
    private String name;
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
