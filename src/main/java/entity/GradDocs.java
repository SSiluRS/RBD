package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "grad_docs")
@org.hibernate.annotations.NamedQuery(name = "GradDocs.all", query = "from GradDocs g")
@org.hibernate.annotations.NamedQuery(name = "GradDocs.byType", query = "from GradDocs g where upper(cast(g.type as string)) like  concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "GradDocs.byNumber", query = "from GradDocs g where upper(cast(g.number as string)) like  concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "GradDocs.byAvScore", query = "from GradDocs g where upper(cast(g.avScore as string)) like  concat('%', upper(?1), '%')")
public class GradDocs {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "type")
    private int type;
    @Basic
    @Column(name = "number")
    private String number;
    @Basic
    @Column(name = "av_score")
    private double avScore;
    @ManyToOne
    @JoinColumn(name = "id_entr", referencedColumnName = "id", nullable = false)
    private Entrant entrantByIdEntr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getAvScore() {
        return avScore;
    }

    public void setAvScore(double avScore) {
        this.avScore = avScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GradDocs gradDocs = (GradDocs) o;

        if (id != gradDocs.id) return false;
        if (type != gradDocs.type) return false;
        if (Double.compare(gradDocs.avScore, avScore) != 0) return false;
        if (number != null ? !number.equals(gradDocs.number) : gradDocs.number != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + type;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        temp = Double.doubleToLongBits(avScore);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public Entrant getEntrantByIdEntr() {
        return entrantByIdEntr;
    }

    public void setEntrantByIdEntr(Entrant entrantByIdEntr) {
        this.entrantByIdEntr = entrantByIdEntr;
    }

    @Override
    public String toString() {
        return  type +
                ", " + number +
                ", " + avScore +
                ", " + entrantByIdEntr.getName();
    }
}
