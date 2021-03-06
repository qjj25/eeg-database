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
 *   ExperimentInfo.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.webservices.client.wrappers;

import java.util.LinkedList;
import java.util.List;

/**
 * Class for gathering important information about experiment. Meant to be sent
 * between eegclient and portal's client service.
 * 
 * @author František Liška
 */
public class ExperimentInfo {
	private int experimentId;
	private int ownerPersonId;
	private int subjectPersonId;
	private int scenarioId;
	private int weatherId;
	private int researchGroupId;
	private long startTimeInMillis;
	private long endTimeInMillis;
	private int temperature;
	private String weatherNote;
	private boolean privateExperiment;
	private List<Integer> hwIds = new LinkedList<Integer>();

	public int getExperimentId() {
		return experimentId;
	}

	public void setExperimentId(int experimentId) {
		this.experimentId = experimentId;
	}

	public int getOwnerPersonId() {
		return ownerPersonId;
	}

	public void setOwnerPersonId(int ownerPersonId) {
		this.ownerPersonId = ownerPersonId;
	}

	public int getSubjectPersonId() {
		return subjectPersonId;
	}

	public void setSubjectPersonId(int subjectPersonId) {
		this.subjectPersonId = subjectPersonId;
	}

	public int getScenarioId() {
		return scenarioId;
	}

	public void setScenarioId(int scenarioId) {
		this.scenarioId = scenarioId;
	}

	public int getWeatherId() {
		return weatherId;
	}

	public void setWeatherId(int weatherId) {
		this.weatherId = weatherId;
	}

	public int getResearchGroupId() {
		return researchGroupId;
	}

	public void setResearchGroupId(int researchGroupId) {
		this.researchGroupId = researchGroupId;
	}

	public long getStartTimeInMillis() {
		return startTimeInMillis;
	}

	public void setStartTimeInMillis(long startTimeInMillis) {
		this.startTimeInMillis = startTimeInMillis;
	}

	public long getEndTimeInMillis() {
		return endTimeInMillis;
	}

	public void setEndTimeInMillis(long endTimeInMillis) {
		this.endTimeInMillis = endTimeInMillis;
	}

	public boolean isPrivateExperiment() {
		return privateExperiment;
	}

	public void setPrivateExperiment(boolean privateExperiment) {
		this.privateExperiment = privateExperiment;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public String getWeatherNote() {
		return weatherNote;
	}

	public void setWeatherNote(String weatherNote) {
		this.weatherNote = weatherNote;
	}

	public List<Integer> getHwIds() {
		return hwIds;
	}

	public void setHwIds(List<Integer> hwIds) {
		this.hwIds = hwIds;
	}
}
