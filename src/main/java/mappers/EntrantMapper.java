package mappers;

import entity.Entrant;
import entity.Faculties;
import entity.Institutions;
import entity.Specializations;

import java.util.List;

public class EntrantMapper extends EntityMapper<Entrant>{

    @Override
    protected Class<Entrant> getType() {
        return Entrant.class;
    }

    @Override
    protected String getTableName() {
        return "Entrant";
    }

    public List<Entrant> findByName(Object name) {
        return findByParameter(".byName", name);
    }
    public List<Entrant> findBySurname(Object surname) {
        return findByParameter(".bySurname", surname);
    }
    public List<Entrant> findByPatronymic(Object patronymic) {
        return findByParameter(".byPatronymic", patronymic);
    }
    public List<Entrant> findBySex(Object sex) {
        return findByParameter(".bySex", sex);
    }
    public List<Entrant> findByNationality(Object nationality) {
        return findByParameter(".byNationality", nationality);
    }
    public List<Entrant> findByBirthday(Object birthday) {
        return findByParameter(".byBirthday", birthday);
    }
    public List<Entrant> findByPassport(Object passport) {
        return findByParameter(".byPassport", passport);
    }
    public List<Entrant> findByAddress(Object address) {
        return findByParameter(".byAddress", address);
    }
    public List<Entrant> findByGradYear(Object gradYear) {
        return findByParameter(".byGradYear", gradYear);
    }
    public List<Entrant> findByEnterExamGroup(Object enterExamGroup) {
        return findByParameter(".byEnterExamGroup", enterExamGroup);
    }
    public List<Entrant> findByFaculty(Object faculty) {
        return ((Faculties)faculty).getEntrants();
    }
    public List<Entrant> findBySpecialization(Object specialization) {
        return ((Specializations)specialization).getEntrantsById();
    }
    public List<Entrant> findByInstitutions(Object institutions) {
        return ((Institutions)institutions).getEntrantsById();
    }
}
