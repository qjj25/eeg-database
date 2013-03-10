package cz.zcu.kiv.eegdatabase.logic.controller.social;

import cz.zcu.kiv.eegdatabase.data.pojo.Person;
import cz.zcu.kiv.eegdatabase.wui.core.person.PersonService;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.linkedin.api.LinkedIn;

import javax.inject.Inject;

/**
 * Class for signing in in via social networks. Invoked when no such
 * user id is found.
 * @author Michal Patočka 
 * 
 */
public final class SocialConnectionSignUp implements ConnectionSignUp {

    private PersonService personService;
    private @Inject LinkedIn linkedin;
   

    public SocialConnectionSignUp(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public String execute(Connection<?> connection) {
        UserProfile profile = connection.fetchUserProfile();

        SocialUser user = new SocialUser(profile.getEmail(),
                profile.getFirstName(), profile.getLastName());
      
        Person person = personService.createPerson(user, null);

        return person.getUsername();

    }
}