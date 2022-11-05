package mappers;

import entity.Faculties;
import entity.Institutions;
import entity.Specializations;

import java.util.List;

public class SpecializationsMapper extends EntityMapper<Specializations> {
    @Override
    protected Class<Specializations> getType() {
        return Specializations.class;
    }

    @Override
    protected String getTableName() {
        return "Specializations";
    }


    public List<Specializations> findByName(Object name){
        return findByParameter(".byName", name);
    }

    public List<Specializations> findByFac(Object faculty){
        return ((Faculties)faculty).getSpecializationsById();
    }
}
