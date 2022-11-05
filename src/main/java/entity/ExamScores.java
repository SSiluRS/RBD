package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "exam_scores")
@org.hibernate.annotations.NamedQuery(name = "ExamScores.all", query = "from ExamScores e")
@org.hibernate.annotations.NamedQuery(name = "ExamScores.byType", query = "from ExamScores e where upper(cast(e.score as string)) like  concat('%', upper(?1), '%')")
public class ExamScores {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "score")
    private int score;
    @ManyToOne
    @JoinColumn(name = "id_entr", referencedColumnName = "id", nullable = false)
    private Entrant entrantByIdEntr;
    @ManyToOne
    @JoinColumn(name = "id_subj", referencedColumnName = "id", nullable = false)
    private Subjects subjectsByIdSubj;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExamScores that = (ExamScores) o;

        if (id != that.id) return false;
        if (score != that.score) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + score;
        return result;
    }

    public Entrant getEntrantByIdEntr() {
        return entrantByIdEntr;
    }

    public void setEntrantByIdEntr(Entrant entrantByIdEntr) {
        this.entrantByIdEntr = entrantByIdEntr;
    }

    public Subjects getSubjectsByIdSubj() {
        return subjectsByIdSubj;
    }

    public void setSubjectsByIdSubj(Subjects subjectsByIdSubj) {
        this.subjectsByIdSubj = subjectsByIdSubj;
    }

    @Override
    public String toString() {
        return  score +
                ", " + getSubjectsByIdSubj().getName() +
                ", " + getEntrantByIdEntr().getName();
    }
}
