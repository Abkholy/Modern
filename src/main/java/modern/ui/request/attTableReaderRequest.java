package modern.ui.request;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class attTableReaderRequest {
    private String hashedQR;
    private String readerMacAddress;
    private Date readingDateTime;

    public String getHashedQR() {
        return hashedQR;
    }

    public void setHashedQR(String hashedQR) {
        this.hashedQR = hashedQR;
    }

    public String getReaderMacAddress() {
        return readerMacAddress;
    }

    public void setReaderMacAddress(String readerMacAddress) {
        this.readerMacAddress = readerMacAddress;
    }

    public Date getReadingDateTime() {
        return readingDateTime;
    }

    public void setReadingDateTime(Date readingDateTime) {
        this.readingDateTime = readingDateTime;
    }
}
