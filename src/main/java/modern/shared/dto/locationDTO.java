package modern.shared.dto;

import modern.io.entity.collage;
import modern.io.entity.locationType;

import java.io.Serializable;

public class locationDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String code;
    private String name;
    private String scannerMacAddress;
    private int capacity;
    private locationType locationType;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScannerMacAddress() {
        return scannerMacAddress;
    }

    public void setScannerMacAddress(String scannerMacAddress) {
        this.scannerMacAddress = scannerMacAddress;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public modern.io.entity.locationType getLocationType() {
        return locationType;
    }

    public void setLocationType(modern.io.entity.locationType locationType) {
        this.locationType = locationType;
    }

    public modern.io.entity.collage getCollage() {
        return collage;
    }

    public void setCollage(modern.io.entity.collage collage) {
        this.collage = collage;
    }
}
