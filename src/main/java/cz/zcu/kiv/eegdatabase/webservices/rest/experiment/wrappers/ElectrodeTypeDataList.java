package cz.zcu.kiv.eegdatabase.webservices.rest.experiment.wrappers;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Data container for collection of electrode type data containers.
 * Able of XML marshaling.
 *
 * @author Petr Miko
 */
@XmlRootElement(name = "electrodeTypes")
public class ElectrodeTypeDataList {

    @XmlElement(name = "electrodeType")
    public List<ElectrodeTypeData> electrodeTypes;

    public ElectrodeTypeDataList(){
        this(new ArrayList<ElectrodeTypeData>());
    }

    public ElectrodeTypeDataList(List<ElectrodeTypeData> electrodeTypes) {
        this.electrodeTypes = electrodeTypes;
    }
}