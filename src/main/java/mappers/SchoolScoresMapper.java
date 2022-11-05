package mappers;

import entity.Entrant;
import entity.Institutions;
import entity.SchoolScores;
import entity.Subjects;

import java.util.List;

public class SchoolScoresMapper extends EntityMapper<SchoolScores> {
    @Override
    protected Class<SchoolScores> getType() {
        return SchoolScores.class;
    }

    @Override
    protected String getTableName() {
        return "SchoolScores";
    }


    public List<SchoolScores> findByScore(Object score){
        return findByParameter(".byScore", score);
    }
    public List<SchoolScores> findByEntr(Object entrant){
        return ((Entrant)entrant).getSchoolScoresById();
    }
    public List<SchoolScores> findBySubj(Object subject){
        return ((Subjects)subject).getSchoolScoresById();
    }

}
