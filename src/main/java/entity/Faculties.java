package entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Set;

@Entity
@org.hibernate.annotations.NamedQuery(name = "Faculties.all", query = "from Faculties f")
@org.hibernate.annotations.NamedQuery(name = "Faculties.byName", query = "from Faculties f where upper(f.name) like  concat('%', upper(?1), '%')")
public class Faculties {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "facultiesByFaculty")
    private List<Entrant> entrants = new java.util.ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "facultiesByFaculty")
    private List<Specializations> specializationsById = new java.util.ArrayList<>();

    public List<Specializations> getSpecializationsById() {
        return specializationsById;
    }

    public void setSpecializationsById(List<Specializations> specializationsById) {
        this.specializationsById = specializationsById;
    }

    public List<Entrant> getEntrants() {
        return entrants;
    }

    public void setEntrants(List<Entrant> entrants) {
        this.entrants = entrants;
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

        Faculties faculties = (Faculties) o;

        if (id != faculties.id) return false;
        if (name != null ? !name.equals(faculties.name) : faculties.name != null) return false;

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
