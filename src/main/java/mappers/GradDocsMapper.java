package mappers;

import entity.Entrant;
import entity.ExamScores;
import entity.GradDocs;
import entity.Subjects;

import java.util.List;

public class GradDocsMapper extends EntityMapper<GradDocs> {
    @Override
    protected Class<GradDocs> getType() {
        return GradDocs.class;
    }

    @Override
    protected String getTableName() {
        return "GradDocs";
    }


    public List<GradDocs> findByEntrant(Object entrant) {
        return ((Entrant)entrant).getGradDocsById();
    }
    public List<GradDocs> findByType(Object type) {
        return findByParameter(".byType", type);
    }
    public List<GradDocs> findByNumber(Object number) {
        return findByParameter(".byNumber", number);
    }
    public List<GradDocs> findByAvScore(Object avScore) {
        return findByParameter(".byAvScore", avScore);
    }
}
