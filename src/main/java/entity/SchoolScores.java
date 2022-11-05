package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "school_scores")

@org.hibernate.annotations.NamedQuery(name = "SchoolScores.all", query = "from SchoolScores s")
@org.hibernate.annotations.NamedQuery(name = "SchoolScores.byType", query = "from SchoolScores s where upper(cast(s.score as string)) like concat('%', upper(?1), '%')")
public class SchoolScores {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "score")
    private int score;

    @Override
    public String toString() {
        return  score +
                ", " + entrantByIdEntr.getName() +
                ", " + subjectsByIdSubj.getName();
    }

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

        SchoolScores that = (SchoolScores) o;

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
}
