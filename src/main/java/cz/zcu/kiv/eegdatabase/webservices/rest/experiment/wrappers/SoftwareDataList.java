package cz.zcu.kiv.eegdatabase.webservices.rest.experiment.wrappers;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;

/**
 * Data container for XML marshaling of used software list.
 *
 * @author Petr Miko
 */
@XmlRootElement(name = "softwareList")
public class SoftwareDataList {

    @XmlElement(name = "software")
    public List<SoftwareData> softwareList;

    public SoftwareDataList(){
        this(Collections.<SoftwareData>emptyList());
    }

    public SoftwareDataList(List<SoftwareData> softwareList) {
       this.softwareList = softwareList;
    }
}