package mappers;

import entity.Faculties;
import entity.Institutions;

import java.util.List;

public class InstitutionsMapper extends EntityMapper<Institutions> {
    @Override
    protected Class<Institutions> getType() {
        return Institutions.class;
    }

    @Override
    protected String getTableName() {
        return "Institutions";
    }


    public List<Institutions> findByName(Object name){
        return findByParameter(".byName", name);
    }

    public List<Institutions> findByType(Object type){
        return findByParameter(".byType", type);
    }
}
