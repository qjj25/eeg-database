package cz.zcu.kiv.eegdatabase.logic.controller.list.personoptparamdef;

import cz.zcu.kiv.eegdatabase.data.dao.AuthorizationManager;
import cz.zcu.kiv.eegdatabase.data.dao.PersonOptParamDefDao;
import cz.zcu.kiv.eegdatabase.data.pojo.PersonOptParamDef;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author JiPER
 */
public class PersonOptParamDefMultiController extends MultiActionController {

    private Log log = LogFactory.getLog(getClass());
    @Autowired
    private AuthorizationManager auth;
    @Autowired
    private PersonOptParamDefDao personOptParamDefDao;

    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Processing person additional parameters list controller");
        ModelAndView mav = new ModelAndView("lists/personAdditionalParams/list");

        mav.addObject("userIsExperimenter", auth.userIsExperimenter());

        List<PersonOptParamDef> list = personOptParamDefDao.getItemsForList();
        mav.addObject("personAdditionalParamsList", list);
        return mav;
    }

    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Deleting personal optional parameter.");
        ModelAndView mav = new ModelAndView("/lists/itemDeleted");

        String idString = request.getParameter("id");
        if (idString != null) {
            int id = Integer.parseInt(idString);

            if (personOptParamDefDao.canDelete(id)) {
                personOptParamDefDao.delete(personOptParamDefDao.read(id));
            } else {
                mav.setViewName("/lists/itemUsed");
            }
        }

        return mav;
    }
}