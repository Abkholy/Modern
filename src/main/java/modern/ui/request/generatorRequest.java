package modern.ui.request;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class generatorRequest {
    private String code;
    private String macAddress;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}
