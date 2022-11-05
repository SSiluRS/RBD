package mappers;

import entity.EnterExamsResults;
import entity.ExamScores;
import entity.Faculties;
import entity.Subjects;

import java.util.List;

public class FacultiesMapper extends EntityMapper<Faculties> {
    @Override
    protected Class<Faculties> getType() {
        return Faculties.class;
    }

    @Override
    protected String getTableName() {
        return "Faculties";
    }


    public List<Faculties> findByName(Object name){
        return findByParameter(".byName", name);
    }
}
