package cz.zcu.kiv.eegdatabase.wui.core.membershipplan;

import cz.zcu.kiv.eegdatabase.data.pojo.ResearchGroup;
import cz.zcu.kiv.eegdatabase.data.pojo.ResearchGroupMembershipPlan;
import cz.zcu.kiv.eegdatabase.wui.core.GenericService;

import java.util.List;

/**
 * Created by Lichous on 6.4.15.
 */
public interface ResearchGroupMembershipPlanService extends GenericService<ResearchGroupMembershipPlan, Integer> {

    List<ResearchGroupMembershipPlan> getGroupMembershipPlans(ResearchGroup researchGroup);

    public boolean isPlanUsed(int membershipPlanId);

}
