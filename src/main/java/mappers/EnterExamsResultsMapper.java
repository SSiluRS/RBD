package mappers;

import entity.EnterExamsResults;
import entity.Entrant;
import entity.Subjects;

import java.util.List;

public class EnterExamsResultsMapper extends EntityMapper<EnterExamsResults> {
    @Override
    protected Class<EnterExamsResults> getType() {
        return EnterExamsResults.class;
    }

    @Override
    protected String getTableName() {
        return "EnterExamsResults";
    }

    public List<EnterExamsResults> findByEntr(Object entrant){
        return ((Entrant)entrant).getEnterExamsResultsById();
    }
    public List<EnterExamsResults> findBySubj(Object subj){
        return ((Subjects)subj).getEnterExamsResultsById();
    }

    public List<EnterExamsResults> findByClassroom(Object classroom){
        return findByParameter(".byClassroom", classroom);
    }
    public List<EnterExamsResults> findByDate(Object date){
        return findByParameter(".byDate", date);
    }
    public List<EnterExamsResults> findByScore(Object score){
        return findByParameter(".byScore", score);
    }
}
