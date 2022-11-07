package entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@org.hibernate.annotations.NamedQuery(name = "Entrant.byName", query = "From Entrant e where upper(e.name) like concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "Entrant.bySurname", query = "from Entrant e where upper(e.surname) like concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "Entrant.byPatronymic", query = "from Entrant e where upper(e.patronymic) like concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "Entrant.bySex", query = "from Entrant e where upper(e.sex) like concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "Entrant.byNationality", query = "from Entrant e where upper(e.nationality) like concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "Entrant.byBirthday", query = "from Entrant e where cast(e.birthday as string) like concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "Entrant.byPassport", query = "from Entrant e where e.passport = ?1")
@org.hibernate.annotations.NamedQuery(name = "Entrant.byAddress", query = "from Entrant e where upper(e.address) like concat('%', upper(?1), '%')")
@org.hibernate.annotations.NamedQuery(name = "Entrant.byGradYear", query = "from Entrant e where e.gradYear = ?1")
@org.hibernate.annotations.NamedQuery(name = "Entrant.byEnterExamGroup", query = "from Entrant e where e.enterExamGroup = ?1")
@org.hibernate.annotations.NamedQuery(name = "Entrant.all", query = "from Entrant e")
@Entity
public class Entrant {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "surname")
    private String surname;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "patronymic")
    private String patronymic;
    @Basic
    @Column(name = "sex")
    private String sex;
    @Basic
    @Column(name = "nationality")
    private String nationality;
    @Basic
    @Column(name = "birthday")
    private Date birthday;
    @Basic
    @Column(name = "passport")
    private String passport;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "grad_year")
    private String gradYear;
    @Basic
    @Column(name = "enter_exam_group")
    private int enterExamGroup;
    @OneToMany(mappedBy = "entrantByIdEntr")
    private List<EnterExamsResults> enterExamsResultsById = new java.util.ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "faculty", referencedColumnName = "id", nullable = false)
    private Faculties facultiesByFaculty;
    @ManyToOne
    @JoinColumn(name = "specialization", referencedColumnName = "id", nullable = false)
    private Specializations specializationsBySpecialization;
    @ManyToOne
    @JoinColumn(name = "institution", referencedColumnName = "id", nullable = false)
    private Institutions institutionsByInstitution;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "entrantByIdEntr")
    private List<ExamScores> examScoresById = new java.util.ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "entrantByIdEntr")
    private List<GradDocs> gradDocsById = new java.util.ArrayList<>();
    @OneToMany(mappedBy = "entrantByChild", fetch = FetchType.EAGER)
    private List<Parents> parentsById = new java.util.ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "entrantByIdEntr")
    private List<SchoolScores> schoolScoresById = new java.util.ArrayList<>();

    public List<EnterExamsResults> getEnterExamsResultsById() {
        return enterExamsResultsById;
    }

    public void setEnterExamsResultsById(List<EnterExamsResults> enterExamsResultsById) {
        this.enterExamsResultsById = enterExamsResultsById;
    }

    public List<ExamScores> getExamScoresById() {
        return examScoresById;
    }

    public void setExamScoresById(List<ExamScores> examScoresById) {
        this.examScoresById = examScoresById;
    }

    public List<Parents> getParentsById() {
        return parentsById;
    }

    public void setParentsById(List<Parents> parentsById) {
        this.parentsById = parentsById;
    }

    public List<SchoolScores> getSchoolScoresById() {
        return schoolScoresById;
    }

    public void setSchoolScoresById(List<SchoolScores> schoolScoresById) {
        this.schoolScoresById = schoolScoresById;
    }

    public List<GradDocs> getGradDocsById() {
        return gradDocsById;
    }

    public void setGradDocsById(List<GradDocs> gradDocsById) {
        this.gradDocsById = gradDocsById;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGradYear() {
        return gradYear;
    }

    public void setGradYear(String gradYear) {
        this.gradYear = gradYear;
    }

    public int getEnterExamGroup() {
        return enterExamGroup;
    }

    public void setEnterExamGroup(int enterExamGroup) {
        this.enterExamGroup = enterExamGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entrant entrant = (Entrant) o;

        if (id != entrant.id) return false;
        if (enterExamGroup != entrant.enterExamGroup) return false;
        if (surname != null ? !surname.equals(entrant.surname) : entrant.surname != null) return false;
        if (name != null ? !name.equals(entrant.name) : entrant.name != null) return false;
        if (patronymic != null ? !patronymic.equals(entrant.patronymic) : entrant.patronymic != null) return false;
        if (sex != null ? !sex.equals(entrant.sex) : entrant.sex != null) return false;
        if (nationality != null ? !nationality.equals(entrant.nationality) : entrant.nationality != null) return false;
        if (birthday != null ? !birthday.equals(entrant.birthday) : entrant.birthday != null) return false;
        if (passport != null ? !passport.equals(entrant.passport) : entrant.passport != null) return false;
        if (address != null ? !address.equals(entrant.address) : entrant.address != null) return false;
        if (gradYear != null ? !gradYear.equals(entrant.gradYear) : entrant.gradYear != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (nationality != null ? nationality.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (passport != null ? passport.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (gradYear != null ? gradYear.hashCode() : 0);
        result = 31 * result + enterExamGroup;
        return result;
    }

    public Faculties getFacultiesByFaculty() {
        return facultiesByFaculty;
    }

    public void setFacultiesByFaculty(Faculties facultiesByFaculty) {
        this.facultiesByFaculty = facultiesByFaculty;
    }

    public Specializations getSpecializationsBySpecialization() {
        return specializationsBySpecialization;
    }

    public void setSpecializationsBySpecialization(Specializations specializationsBySpecialization) {
        this.specializationsBySpecialization = specializationsBySpecialization;
    }

    public Institutions getInstitutionsByInstitution() {
        return institutionsByInstitution;
    }

    public void setInstitutionsByInstitution(Institutions institutionsByInstitution) {
        this.institutionsByInstitution = institutionsByInstitution;
    }

    @Override
    public String toString() {
        String parents = "Родители: ";
        int i = 1;
        for (Parents parent :
                parentsById) {
            parents+="Родитель " + i + ": "+ parent + " ";
            i+=1;
        }
        return  surname +
                " " + name +
                " " + patronymic +
                ", " + sex +
                ", " + nationality +
                ", " + birthday +
                ", " + passport +
                ", " + address +
                ", " + gradYear +
                ", " + enterExamGroup +
                ", " + facultiesByFaculty.getName() +
                ", " + specializationsBySpecialization.getName() +
                ", " + institutionsByInstitution.getName()+
                ", " + parents;
    }
}
