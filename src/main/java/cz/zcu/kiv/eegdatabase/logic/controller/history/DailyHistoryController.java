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
 *   DailyHistoryController.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zcu.kiv.eegdatabase.logic.controller.history;

import cz.zcu.kiv.eegdatabase.logic.controller.myaccount.ChangeDefaultGroupCommand;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Sets reference data for form view and prepared ModelAndView for action "on submit"(for choice daily history )
 * using object change default group command for saving select group id
 *
 * @author pbruha
 */
public class DailyHistoryController extends AbstractHistoryController {

    public DailyHistoryController() {
        setCommandClass(ChangeDefaultGroupCommand.class);
        setCommandName("changeDefaultGroup");
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException bindException) throws Exception {
        log.debug("Processing daily download history");
        ModelAndView mav = new ModelAndView(getSuccessView());
        ChangeDefaultGroupCommand changeDefaultGroupCommand = (ChangeDefaultGroupCommand) command;
        mav = super.onSubmit(ChoiceHistory.DAILY, changeDefaultGroupCommand, mav);
        log.debug("Returning MAV");
        return mav;
    }

    @Override
    protected Map referenceData(HttpServletRequest request) throws Exception {
        log.debug("Processing daily download history");
        Map map = new HashMap<String, Object>();
        map = super.setReferenceData(map, ChoiceHistory.DAILY);
        return map;
    }
}
