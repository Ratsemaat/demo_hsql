package ee.ut.tvt.hibernateDemo.model;

import javax.persistence.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

@Entity
@Table(name = "LECTURER")
public class Lecturer {

    private static final Format FORMATTER = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "academic_degree")
    private String academicDegree;

    @OneToMany(mappedBy = "lecturer")
    private Set<Course> courses;

    public Set<Course> getCourses() {
        return courses;
    }

    private String getBirthDateString() {
        return birthDate == null ? "?" : FORMATTER.format(birthDate);  // birthDate.toString();
    }

    private String getDegreeString() {
        return academicDegree == null ? "?" : academicDegree;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" -- ").append(firstName).append(" ").append(lastName).append(" (degree: ").append(getDegreeString()).append(", date of birth: ").append(getBirthDateString()).append(")\n");
        sb.append("      Teaches following courses: \n");
        if (courses != null) {
            for (Course c : courses) {
                sb.append("       * ").append(c.getEstonianName()).append("\n");
            }
        } else {
            sb.append("       ?");
        }

        return sb.toString();
    }


}
