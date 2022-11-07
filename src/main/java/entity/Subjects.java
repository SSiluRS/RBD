package entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

@Entity
@org.hibernate.annotations.NamedQuery(name = "Subjects.all", query = "from Subjects i")
@org.hibernate.annotations.NamedQuery(name = "Subjects.byName", query = "from Subjects i where upper(i.name) like concat('%', upper(?1), '%')")
public class Subjects {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "subjectsByIdSubj")
    private List<EnterExamsResults> enterExamsResultsById = new java.util.ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "subjectsByIdSubj")
    private List<ExamScores> examScoresById = new java.util.ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "subjectsByIdSubj")
    private List<SchoolScores> schoolScoresById = new java.util.ArrayList<>();

    public List<SchoolScores> getSchoolScoresById() {
        return schoolScoresById;
    }

    public void setSchoolScoresById(List<SchoolScores> schoolScoresById) {
        this.schoolScoresById = schoolScoresById;
    }

    public List<ExamScores> getExamScoresById() {
        return examScoresById;
    }

    public void setExamScoresById(List<ExamScores> examScoresById) {
        this.examScoresById = examScoresById;
    }

    public List<EnterExamsResults> getEnterExamsResultsById() {
        return enterExamsResultsById;
    }

    public void setEnterExamsResultsById(List<EnterExamsResults> enterExamsResultsById) {
        this.enterExamsResultsById = enterExamsResultsById;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subjects subjects = (Subjects) o;

        if (id != subjects.id) return false;
        if (name != null ? !name.equals(subjects.name) : subjects.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
