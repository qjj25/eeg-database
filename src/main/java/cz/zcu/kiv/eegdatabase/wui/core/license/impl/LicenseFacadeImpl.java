/*******************************************************************************
 * This file is part of the EEG-database project
 * 
 *   ==========================================
 *  
 *   Copyright (C) 2013 by University of West Bohemia (http://www.zcu.cz/en/)
 *  
 *  ***********************************************************************************************************************
 *  
 *   Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 *   the License. You may obtain a copy of the License at
 *  
 *       http://www.apache.org/licenses/LICENSE-2.0
 *  
 *   Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 *   an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 *   specific language governing permissions and limitations under the License.
 *  
 *  ***********************************************************************************************************************
 *  
 *   LicenseFacadeImpl.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.wui.core.license.impl;

import cz.zcu.kiv.eegdatabase.data.pojo.*;
import cz.zcu.kiv.eegdatabase.wui.core.GenericFacadeImpl;
import cz.zcu.kiv.eegdatabase.wui.core.GenericService;
import cz.zcu.kiv.eegdatabase.wui.core.license.LicenseFacade;
import cz.zcu.kiv.eegdatabase.wui.core.license.LicenseService;
import cz.zcu.kiv.eegdatabase.wui.core.license.PersonalLicenseService;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

/**
 *
 * @author J. Danek
 */
public class LicenseFacadeImpl extends GenericFacadeImpl<License, Integer> implements LicenseFacade {

	
	private LicenseService licenseService;

	private PersonalLicenseService personalLicenseService;
	
    public LicenseFacadeImpl() {
    }

    public LicenseFacadeImpl(GenericService<License, Integer> service) {
	super(service);
    }
	
	@Required
	public void setLicenseService(LicenseService licenseRervice)
	{
		this.licenseService = licenseRervice;
	}
	
	@Required
	public void setPersonalLicenseService(PersonalLicenseService personalLicenseService)
	{
		this.personalLicenseService = personalLicenseService;
	}

	@Override
	public void removeLicenseFromPackage(License license, ExperimentPackage pack) {
		this.licenseService.removeLicenseFromPackage(license, pack);
	}

	@Override
	public void createRequestForLicense(PersonalLicense personalLicense) {
		this.personalLicenseService.createRequestForLicense(personalLicense);
	}

	@Override
	public void confirmRequestForLicense(PersonalLicense personalLicense) {
		this.personalLicenseService.confirmRequestForLicense(personalLicense);
	}

	@Override
	public List<PersonalLicense> getLicenseRequests(Person applicant, PersonalLicenseState state) {
		return this.personalLicenseService.getLicenseRequests(applicant, state);
	}

	@Override
	public List<PersonalLicense> getRevokedRequests(ResearchGroup group) {
		return this.personalLicenseService.getLicenseRequests(group, PersonalLicenseState.REJECTED);
	}

	@Override
	public boolean addLicenseToPerson(License licence, Person person) {
		return this.personalLicenseService.addLicenseToPerson(person, licence);
	}

	@Override
	public List<License> getLicensesByType(LicenseType type) {
		return licenseService.getLicensesByType(type);
	}

	@Override
	public List<License> getLicensesByType(List<LicenseType> type) {
		return licenseService.getLicensesByType(type);
	}

	@Override
	public List<License> getLicensesForPackage(ExperimentPackage pckg) {
		return licenseService.getLicensesForPackage(pckg);
	}

	@Override
	public void rejectRequestForLicense(PersonalLicense personalLicense) {
		this.personalLicenseService.rejectRequestForLicense(personalLicense);
	}

	@Override
	public List<PersonalLicense> getGrantedLicenses(ResearchGroup group) {
		return personalLicenseService.getLicenseRequests(group, PersonalLicenseState.AUTHORIZED);
	}

	@Override
	public List<License> getUsersLicenses(Person person) {
		return personalLicenseService.getUsersLicenses(person);
	}

	@Override
	public List<PersonalLicense> getLicenseRequests(ResearchGroup group) {
		return personalLicenseService.getLicenseRequests(group, PersonalLicenseState.APPLICATION);
	}

	@Override
	public void updatePersonalLicense(PersonalLicense license) {
		personalLicenseService.update(license);
	}

    @Override
    public byte[] getPersonalLicenseAttachmentContent(int personalLicenseId) {
        return personalLicenseService.getPersonalLicenseAttachmentContent(personalLicenseId);
    }

    @Override
    public byte[] getLicenseAttachmentContent(int licenseId) {
        return licenseService.getLicenseAttachmentContent(licenseId);
    }

    @Override
    public List<License> getLicenseForPackageAndOwnedByPerson(int personId, int packageId) {
        return licenseService.getLicenseForPackageAndOwnedByPerson(personId, packageId);
    }

    @Override
    public License getLicenseForPurchasedExperiment(int experimentId, int personId) {
        return licenseService.getLicenseForPurchasedExperiment(experimentId, personId);
    }

    @Override
    public License getLicenseForPurchasedExpPackage(int experimentPackageId, int personId) {
        return licenseService.getLicenseForPurchasedExpPackage(experimentPackageId, personId);
    }

    @Override
    public List<License> getLicensesForExperiment(int experimentId) {
        return licenseService.getLicensesForExperiment(experimentId);

    }

    @Override
    public List<License> getPersonLicenses(int personId)  {
        return licenseService.getPersonLicenses(personId);
    }

    @Override
    public void removeLicenseFromExperiment(Experiment exp, License license) {
        licenseService.removeLicenseFromExperiment(exp, license);

    }

}
