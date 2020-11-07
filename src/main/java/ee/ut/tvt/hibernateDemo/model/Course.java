package ee.ut.tvt.hibernateDemo.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "COURSE")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "estonian_name")
    private String estonianName;

    @Column(name = "english_name")
    private String englishName;

    @ManyToOne
    @JoinColumn(name = "lecturer_id", nullable = false)
    private Lecturer lecturer;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;

    @ManyToMany
    @JoinTable(
            name = "COURSES_TO_SPECIALITIES",
            joinColumns = @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "SPECIALITY_ID", referencedColumnName = "ID")
    )
    private Set<Speciality> specialities;

    public Lecturer getLecturer() {
        return lecturer;
    }

    public String getEstonianName() {
        return estonianName;
    }

    private String getEnglishName() {
        return englishName == null ? "?" : englishName;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" -- ").append(estonianName).append(" [").append(getEnglishName()).append("]\n");
        sb.append("      Belongs to following specialities: \n");
        if (specialities != null) {
            for (Speciality s : specialities) {
                sb.append("       * ").append(s.getName()).append("\n");
            }
        } else {
            sb.append("       ?");
        }
        return sb.toString();
    }

}
