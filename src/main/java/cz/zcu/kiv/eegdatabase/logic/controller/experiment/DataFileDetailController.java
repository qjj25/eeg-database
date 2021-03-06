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
 *   DataFileDetailController.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.logic.controller.experiment;

import cz.zcu.kiv.eegdatabase.data.dao.AuthorizationManager;
import cz.zcu.kiv.eegdatabase.data.dao.FileMetadataParamDefDao;
import cz.zcu.kiv.eegdatabase.data.dao.GenericDao;
import cz.zcu.kiv.eegdatabase.data.pojo.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JiPER
 */
public class DataFileDetailController
        extends SimpleFormController
        implements Validator {

    private Log log = LogFactory.getLog(getClass());
    private AuthorizationManager auth;
    private GenericDao<DataFile, Integer> dataFileDao;
    private GenericDao<FileMetadataParamVal, FileMetadataParamValId> fileMetadataParamValDao;
    private FileMetadataParamDefDao fileMetadataParamDefDao;

    public DataFileDetailController() {
        setCommandClass(AddFileMetadataCommand.class);
        setCommandName("addMetadata");
    }

    @Override
    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) throws Exception {
        ModelAndView mav = super.showForm(request, response, errors);

        int fileId = 0;
        try {
            fileId = Integer.parseInt(request.getParameter("fileId"));
        } catch (Exception e) {
        }

        boolean val = auth.userIsOwnerOrCoexpOfCorrespExperiment(fileId);
        mav.addObject("userIsOwnerOrCoexpOfCorrespExperiment", val);

        boolean userIsExperimenter = auth.userIsExperimenter();
        mav.addObject("userIsExperimenter", userIsExperimenter);

        return mav;
    }


    @Override
    protected Map referenceData(HttpServletRequest request, Object command, Errors errors) throws Exception {
        Map map = new HashMap<String, Object>();

        int fileId = Integer.parseInt(request.getParameter("fileId"));
        DataFile data = dataFileDao.read(fileId);
        map.put("researchGroupTitle",data.getExperiment().getResearchGroup().getTitle());
        map.put("dataDetail", data);
        List<FileMetadataParamDef> list = fileMetadataParamDefDao.getRecordsByGroup(data.getExperiment().getResearchGroup().getResearchGroupId());
        map.put("fileMetadataParams", list);

        return map;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        AddFileMetadataCommand addFileMetadataCommand = (AddFileMetadataCommand) command;

        int fileId = Integer.parseInt(request.getParameter("fileId"));

        ModelAndView mav = new ModelAndView("redirect:/experiments/data/detail.html?fileId=" + fileId);

        log.debug("Checking the permission.");
        if (!auth.userIsOwnerOrCoexpOfCorrespExperiment(fileId)) {
            log.debug("User does not have permission to add metadata value - no data saved, returning MAV.");
            return mav;
        }

        log.debug("Creating new FileMetadata object");
        FileMetadataParamVal metadata = new FileMetadataParamVal();
        metadata.setId(new FileMetadataParamValId(addFileMetadataCommand.getParamId(), fileId));

        log.debug("Setting the metadata value = " + addFileMetadataCommand.getParamValue());
        metadata.setMetadataValue(addFileMetadataCommand.getParamValue());

        log.debug("Saving new file metadata entry");
        fileMetadataParamValDao.create(metadata);

        log.debug("Returning MAV");
        return mav;
    }

    public boolean supports(Class clazz) {
        return clazz.equals(AddFileMetadataCommand.class);
    }

    public void validate(Object command, Errors errors) {
        AddFileMetadataCommand data = (AddFileMetadataCommand) command;
        log.debug("Validating form for adding file metadata.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "paramValue", "required.paramValue");

        if (data.getParamId() <= 0) {
            errors.rejectValue("paramId", "required.paramId");
        }

        FileMetadataParamVal f = fileMetadataParamValDao.read(new FileMetadataParamValId(data.getParamId(), data.getDataId()));
        if (f != null) {
            errors.rejectValue("paramId", "invalid.paramIdAlreadyInserted");
        }
    }



    public GenericDao<DataFile, Integer> getDataFileDao() {
        return dataFileDao;
    }

    public void setDataFileDao(GenericDao<DataFile, Integer> dataFileDao) {
        this.dataFileDao = dataFileDao;
    }

    public GenericDao<FileMetadataParamVal, FileMetadataParamValId> getFileMetadataParamValDao() {
        return fileMetadataParamValDao;
    }

    public void setFileMetadataParamValDao(GenericDao<FileMetadataParamVal, FileMetadataParamValId> fileMetadataParamValDao) {
        this.fileMetadataParamValDao = fileMetadataParamValDao;
    }

    public FileMetadataParamDefDao getFileMetadataParamDefDao() {
        return fileMetadataParamDefDao;
    }

    public void setFileMetadataParamDefDao(FileMetadataParamDefDao fileMetadataParamDefDao) {
        this.fileMetadataParamDefDao = fileMetadataParamDefDao;
    }

    public AuthorizationManager getAuth() {
        return auth;
    }

    public void setAuth(AuthorizationManager auth) {
        this.auth = auth;
    }
}
