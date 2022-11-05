package entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "enter_exams_results")
@org.hibernate.annotations.NamedQuery(name = "EnterExamsResults.byClassroom", query = "from EnterExamsResults e where upper(cast(e.classroom as string)) like  concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "EnterExamsResults.byDate", query = "from EnterExamsResults e where upper(cast(e.date as string)) like  concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "EnterExamsResults.byScore", query = "from EnterExamsResults e where upper(cast(e.score as string)) like  concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "EnterExamsResults.all", query = "from EnterExamsResults e")
public class EnterExamsResults {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "Classroom")
    private int classroom;
    @Basic
    @Column(name = "Date")
    private Timestamp date;
    @Basic
    @Column(name = "Score")
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

    public int getClassroom() {
        return classroom;
    }

    public void setClassroom(int classroom) {
        this.classroom = classroom;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
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

        EnterExamsResults that = (EnterExamsResults) o;

        if (id != that.id) return false;
        if (classroom != that.classroom) return false;
        if (score != that.score) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + classroom;
        result = 31 * result + (date != null ? date.hashCode() : 0);
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
        return classroom +
                ", " + date +
                ", " + score +
                ", " + getSubjectsByIdSubj().getName() +
                ", " + getEntrantByIdEntr().getName();
    }
}

