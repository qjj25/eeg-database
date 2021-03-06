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
 *   MetadataCommand.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.zcu.kiv.eegdatabase.logic.controller.experiment;

import java.io.Serializable;

/**
 * @author Jan Štěbeták
 * 
 * UPDATE: Used and improved in wicket for download experiment.
 * 
 * Added check logic for ExperimentDownloadPage. 
 * 
 * chooseAll just check all groups on pages. 
 * 
 * check groups is:
 * Person - all information about person
 * Scenario - all information about scenario
 * Measuration - all information about experiment
 * Files - check all files and parameters.
 */
public class MetadataCommand implements Serializable {

    private static final long serialVersionUID = -6561777586934738318L;
    
    private boolean chooseAll;
    private boolean person;
    private boolean scenario;
    private boolean measuration;

    private boolean name;
    private boolean birth;
    private boolean gender;
    private boolean phoneNumber;
    private boolean email;
    private boolean note;
   // private boolean eyesDefects;
    //private boolean hearingDefects;
    private boolean personAddParams;

    private boolean title;
    private boolean length;
    private boolean description;
    private boolean scenFile;


    private boolean times;
    private boolean temperature;
    private boolean weather;
    private boolean weatherNote;
    private boolean hardware;
    private boolean measurationAddParams;
    private boolean samplingRate;
    private boolean fileName;
    private boolean fileMetadata;

    public boolean isFileMetadata() {
        return fileMetadata;
    }

    public boolean isScenFile() {
        return scenFile;
    }

    public void setScenFile(boolean scenFile) {
        this.scenFile = scenFile;
    }

    public boolean isMeasurationAddParams() {
        return measurationAddParams;
    }

    public boolean isBirth() {
        return birth;
    }

    public boolean isChooseAll() {
        return chooseAll;
    }

    public boolean isDescription() {
        return description;
    }

    public boolean isEmail() {
        return email;
    }

//    public boolean isEyesDefects() {
//        return eyesDefects;
//    }

    public boolean isGender() {
        return gender;
    }

    public boolean isHardware() {
        return hardware;
    }

//    public boolean isHearingDefects() {
//        return hearingDefects;
//    }

    public boolean isLength() {
        return length;
    }

    public boolean isName() {
        return name;
    }

    public boolean isNote() {
        return note;
    }

    public boolean isPerson() {
        return person;
    }

    public boolean isPersonAddParams() {
        return personAddParams;
    }

    public boolean isPhoneNumber() {
        return phoneNumber;
    }

    public boolean isSamplingRate() {
        return samplingRate;
    }

    public boolean isScenario() {
        return scenario;
    }

    public boolean isTemperature() {
        return temperature;
    }

    public boolean isTimes() {
        return times;
    }

    public boolean isTitle() {
        return title;
    }

    public boolean isWeather() {
        return weather;
    }

    public boolean isWeatherNote() {
        return weatherNote;
    }

    public boolean isMeasuration() {
        return measuration;
    }

    public boolean isFileName() {
        return fileName;
    }

    public void setFileName(boolean fileName) {
        this.fileName = fileName;
    }

    public void setFileMetadata(boolean fileMetadata) {
        this.fileMetadata = fileMetadata;
    }

    public void setMeasuration(boolean measuration) {
        this.measuration = measuration;
        setTimes(measuration);
        setTemperature(measuration);
        setWeather(measuration);
        setWeatherNote(measuration);
        setHardware(measuration);
        setMeasurationAddParams(measuration);
    }

    public void setMeasurationAddParams(boolean measurationAddParams) {
        this.measurationAddParams = measurationAddParams;
    }

    public void setBirth(boolean birth) {
        this.birth = birth;
    }

    public void setChooseAll(boolean chooseAll) {
        this.chooseAll = chooseAll;
        setPerson(chooseAll);
        setScenario(chooseAll);
        setMeasuration(chooseAll);
    }

    public void setDescription(boolean description) {
        this.description = description;
    }

    public void setEmail(boolean email) {
        this.email = email;
    }

//    public void setEyesDefects(boolean eyesDefects) {
//        this.eyesDefects = eyesDefects;
//    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setHardware(boolean hardware) {
        this.hardware = hardware;
    }

//    public void setHearingDefects(boolean hearingDefects) {
//        this.hearingDefects = hearingDefects;
//    }

    public void setLength(boolean length) {
        this.length = length;
    }

    public void setName(boolean name) {
        this.name = name;
    }

    public void setNote(boolean note) {
        this.note = note;
    }

    public void setPerson(boolean person) {
        this.person = person;
        setName(person);
        setNote(person);
        setBirth(person);
        setGender(person);
        setPhoneNumber(person);
        setEmail(person);
        setPersonAddParams(person);
    }

    public void setPersonAddParams(boolean personAddParams) {
        this.personAddParams = personAddParams;
    }

    public void setPhoneNumber(boolean phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSamplingRate(boolean samplingRate) {
        this.samplingRate = samplingRate;
    }

    public void setScenario(boolean scenario) {
        this.scenario = scenario;
        setTitle(scenario);
        setLength(scenario);
        setDescription(scenario);
        setScenFile(scenario);
    }

    public void setTemperature(boolean temperature) {
        this.temperature = temperature;
    }

    public void setTimes(boolean times) {
        this.times = times;
    }

    public void setTitle(boolean title) {
        this.title = title;
    }

    public void setWeather(boolean weather) {
        this.weather = weather;
    }

    public void setWeatherNote(boolean weatherNote) {
        this.weatherNote = weatherNote;
    }

}
