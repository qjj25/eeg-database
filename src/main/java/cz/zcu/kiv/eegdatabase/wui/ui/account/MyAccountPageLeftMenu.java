package cz.zcu.kiv.eegdatabase.wui.ui.account;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import cz.zcu.kiv.eegdatabase.wui.components.menu.button.IButtonPageMenu;
import cz.zcu.kiv.eegdatabase.wui.components.page.MenuPage;

public enum MyAccountPageLeftMenu implements IButtonPageMenu {

    ACCOUNT_OVERVIEW(AccountOverViewPage.class, "menuItem.myAccount.overview", null),
    CHANGE_PASSWORD(ChangePasswordPage.class, "menuItem.myAccount.changePassword", null),
    SOCIAL(SocialNetworksPage.class, "menuItem.myAccount.social", null), ;

    private Class<? extends MenuPage> pageClass;
    private String pageTitleKey;
    private PageParameters parameters;

    private MyAccountPageLeftMenu(Class<? extends MenuPage> pageClass, String pageTitleKey, PageParameters parameters) {
        this.pageClass = pageClass;
        this.pageTitleKey = pageTitleKey;
        this.parameters = parameters;
    }

    @Override
    public Class<? extends MenuPage> getPageClass() {
        return pageClass;
    }

    @Override
    public String getPageTitleKey() {
        return pageTitleKey;
    }

    @Override
    public PageParameters getPageParameters() {
        return parameters;
    }

}
