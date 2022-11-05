package entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

@Entity
@org.hibernate.annotations.NamedQuery(name = "Institutions.all", query = "from Institutions i")
@org.hibernate.annotations.NamedQuery(name = "Institutions.byName", query = "from Institutions i where upper(i.name) like concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "Institutions.byType", query = "from Institutions i where upper(i.type) like concat('%', upper(?1), '%')")
public class Institutions {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "type")
    private String type;
    @OneToMany(mappedBy = "institutionsByInstitution")
    private List<Entrant> entrantsById = new java.util.ArrayList<>();

    public List<Entrant> getEntrantsById() {
        return entrantsById;
    }

    public void setEntrantsById(List<Entrant> entrantsById) {
        this.entrantsById = entrantsById;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Institutions that = (Institutions) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String t = type == "2"? "Школа" : "Колледж";
        return  name +
                ", " + t;
    }
}
