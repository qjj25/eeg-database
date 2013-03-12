package cz.zcu.kiv.eegdatabase.wui.core.person;

import java.sql.Timestamp;
import java.util.Date;

import org.joda.time.DateTime;

import cz.zcu.kiv.eegdatabase.data.pojo.Person;
import cz.zcu.kiv.eegdatabase.wui.core.dto.FullPersonDTO;
import cz.zcu.kiv.eegdatabase.wui.ui.security.Gender;

public class PersonMapper {

    public FullPersonDTO convertToDTO(Person person) {

        FullPersonDTO dto = new FullPersonDTO();
        dto.setId(person.getPersonId());
        dto.setName(person.getGivenname());
        dto.setSurname(person.getSurname());
        dto.setDateOfBirth(person.getDateOfBirth() == null ? null : new Date(person.getDateOfBirth().getTime()));
        dto.setEmail(person.getUsername().toLowerCase());
        dto.setUsername(person.getUsername());
        dto.setGender(Gender.getGenderByShortcut(person.getGender()));
        dto.setConfirmed(person.isConfirmed());
        dto.setRegistrationDate(new DateTime(person.getRegistrationDate().getTime()));

        dto.setEducationLevel(person.getEducationLevel());
        dto.setLaterality(person.getLaterality());
        dto.setAuthority(person.getAuthority());

        return dto;
    }

    public Person convertToEntity(FullPersonDTO dto, Person person) {

        person.setPersonId(dto.getId());
        person.setGivenname(dto.getName());
        person.setSurname(dto.getSurname());
        person.setDateOfBirth(new Timestamp(dto.getDateOfBirth().getTime()));
        person.setUsername(dto.getEmail().toLowerCase());
        person.setGender(dto.getGender().getShortcut());
        person.setConfirmed(dto.isConfirmed());
        person.setRegistrationDate(new Timestamp(dto.getRegistrationDate().getMillis()));
        person.setLaterality(dto.getLaterality());
        person.setAuthority(dto.getAuthority());
        person.setPassword(dto.getPassword());
        person.setEducationLevel(dto.getEducationLevel());

        return person;
    }
}
