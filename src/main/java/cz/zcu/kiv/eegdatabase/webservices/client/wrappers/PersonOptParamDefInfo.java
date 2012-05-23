package cz.zcu.kiv.eegdatabase.webservices.client.wrappers;

import java.util.LinkedList;
import java.util.List;

/**
 * @author František Liška
 */
public class PersonOptParamDefInfo {
    private int personOptParamDefId;
    private String paramName;
    private String paramDataType;
    private int defaultNumber;
    private List<Integer> researchGroupIdList = new LinkedList<Integer>();

    public int getPersonOptParamDefId() {
        return personOptParamDefId;
    }

    public void setPersonOptParamDefId(int personOptParamDefId) {
        this.personOptParamDefId = personOptParamDefId;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamDataType() {
        return paramDataType;
    }

    public void setParamDataType(String paramDataType) {
        this.paramDataType = paramDataType;
    }

    public int getDefaultNumber() {
        return defaultNumber;
    }

    public void setDefaultNumber(int defaultNumber) {
        this.defaultNumber = defaultNumber;
    }

    public List<Integer> getResearchGroupIdList() {
        return researchGroupIdList;
    }

    public void setResearchGroupIdList(List<Integer> researchGroupIdList) {
        this.researchGroupIdList = researchGroupIdList;
    }
}
