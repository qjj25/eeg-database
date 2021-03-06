package cz.zcu.kiv.eegdatabase.wui.core.experimentpackage;

import java.util.List;

import cz.zcu.kiv.eegdatabase.data.pojo.ExperimentPackage;
import cz.zcu.kiv.eegdatabase.data.pojo.ExperimentPackageLicense;
import cz.zcu.kiv.eegdatabase.data.pojo.License;
import cz.zcu.kiv.eegdatabase.wui.core.GenericFacade;

/**
 * Created by Lichous on 11.5.15.
 */
public interface ExperimentPackageLicenseFacade extends GenericFacade<ExperimentPackageLicense, Integer> {
    
    /**
     * List all experiment package licenses for the given package.
     *
     * @param pckg the package
     * @return list of experiment package licenses
     */
    public List<ExperimentPackageLicense> getExperimentPackageLicensesForPackage(ExperimentPackage pckg);
    
    /**
     * Find the experiment package license object for the given package and license.
     * 
     * @param pckg the package
     * @param license the license
     * @return the experiment package license or null
     */
    public ExperimentPackageLicense getExperimentPackageLicense(ExperimentPackage pckg, License license);

}
