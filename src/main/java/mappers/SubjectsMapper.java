package mappers;

import entity.Subjects;

import java.util.List;

public class SubjectsMapper extends EntityMapper<Subjects> {
    @Override
    protected Class<Subjects> getType() {
        return Subjects.class;
    }

    @Override
    protected String getTableName() {
        return "Subjects";
    }

    public List<Subjects> findByName(Object name){
        return findByParameter(".byName", name);
    }
}
