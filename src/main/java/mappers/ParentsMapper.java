package mappers;

import entity.Entrant;
import entity.Parents;

import java.util.List;

public class ParentsMapper extends EntityMapper<Parents> {
    @Override
    protected Class<Parents> getType() {
        return Parents.class;
    }

    @Override
    protected String getTableName() {
        return "Parents";
    }

    public List<Parents> findByName(Object name) {
        return findByParameter(".byName", name);
    }
    public List<Parents> findBySurname(Object surname) {
        return findByParameter(".bySurname", surname);
    }
    public List<Parents> findByPatronymic(Object patronymic) {
        return findByParameter(".byPatronymic", patronymic);
    }
    public List<Parents> findByAddress(Object address) {
        return findByParameter(".byAddress", address);
    }
    public List<Parents> findByChild(Object child) {
        return ((Entrant)child).getParentsById();
    }
}
