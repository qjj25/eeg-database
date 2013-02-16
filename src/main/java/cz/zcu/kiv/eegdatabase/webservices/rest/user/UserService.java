package cz.zcu.kiv.eegdatabase.webservices.rest.user;

import cz.zcu.kiv.eegdatabase.webservices.rest.common.exception.RestServiceException;
import cz.zcu.kiv.eegdatabase.webservices.rest.user.wrappers.ExperimentData;
import cz.zcu.kiv.eegdatabase.webservices.rest.user.wrappers.ExperimentDataList;
import cz.zcu.kiv.eegdatabase.webservices.rest.user.wrappers.ResearchGroupDataList;
import cz.zcu.kiv.eegdatabase.webservices.rest.user.wrappers.UserInfo;

import java.util.List;

/**
 * @author Petr Miko
 *         Date: 10.2.13
 */
public interface UserService {

    public UserInfo login();

    public ResearchGroupDataList getMyGroups() throws RestServiceException;

    public ExperimentDataList getMyExperiments()  throws RestServiceException;

}
