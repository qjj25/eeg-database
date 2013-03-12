package cz.zcu.kiv.eegdatabase.wui.components.utils;

import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import cz.zcu.kiv.eegdatabase.wui.components.page.BasePage;

public class PageParametersUtils {

    public static final String GROUP_PARAM = "GROUP";

    public static PageParameters getPageParameters(String key, Object object) {

        PageParameters param = new PageParameters();
        param.add(key, object);

        return param;
    }

    public static PageParameters getDefaultPageParameters(Object object) {

        return getPageParameters(BasePage.DEFAULT_PARAM_ID, object);
    }

    public static PageParameters addParameters(PageParameters parameters,
            String key, Object object) {

        parameters.add(key, object);
        return parameters;
    }

    public static String getUrlForPage(Class page, PageParameters parameters) {

        return RequestCycle.get().getUrlRenderer().renderFullUrl(
                Url.parse(RequestCycle.get().urlFor(page, parameters).toString()));
    }
}
