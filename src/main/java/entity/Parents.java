package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@org.hibernate.annotations.NamedQuery(name = "Parents.byName", query = "From Parents p where upper(p.name) like concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "Parents.bySurname", query = "from Parents p where upper(p.surname) like concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "Parents.byPatronymic", query = "from Parents p where upper(p.patronymic) like concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "Parents.byAddress", query = "from Parents p where upper(p.address) like concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "Parents.all", query = "from Parents p")
public class Parents {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "surname")
    private String surname;

    @Override
    public String toString() {
        return
                name +
                " " + surname +
                " " + patronymic +
                ", " + address;
    }

    @Basic
    @Column(name = "patronymic")
    private String patronymic;
    @Basic
    @Column(name = "address")
    private String address;
    @ManyToOne
    @JoinColumn(name = "child", referencedColumnName = "id", nullable = false)
    private Entrant entrantByChild;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parents parents = (Parents) o;

        if (id != parents.id) return false;
        if (name != null ? !name.equals(parents.name) : parents.name != null) return false;
        if (surname != null ? !surname.equals(parents.surname) : parents.surname != null) return false;
        if (patronymic != null ? !patronymic.equals(parents.patronymic) : parents.patronymic != null) return false;
        if (address != null ? !address.equals(parents.address) : parents.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    public Entrant getEntrantByChild() {
        return entrantByChild;
    }

    public void setEntrantByChild(Entrant entrantByChild) {
        this.entrantByChild = entrantByChild;
    }
}
