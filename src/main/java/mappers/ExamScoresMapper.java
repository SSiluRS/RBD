package mappers;

import entity.Entrant;
import entity.ExamScores;
import entity.Institutions;
import entity.Subjects;

import java.util.List;

public class ExamScoresMapper extends EntityMapper<ExamScores>{

    @Override
    protected Class<ExamScores> getType() {
        return ExamScores.class;
    }

    @Override
    protected String getTableName() {
        return "ExamScores";
    }


    public List<ExamScores> findByEntr(Object entrant) {
        return ((Entrant)entrant).getExamScoresById();
    }
    public List<ExamScores> findBySubj(Object subj) {
        return ((Subjects)subj).getExamScoresById();
    }
    public List<ExamScores> findByScore(Object score) {
        return findByParameter(".byScore", score);
    }
}
