package ee.ut.tvt.hibernateDemo.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "SPECIALITY")
public class Speciality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "department")
    private String department;

    @ManyToMany(mappedBy ="specialities")
    private Set<Course> courses;

    @OneToMany(mappedBy = "speciality")
    private Set<Student> students;

    public String getName() {
        return name;
    }

    private String getDepartmentString() {
        return department == null ? "?" : department;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" -- ").append(name).append(" (faculty : ").append(getDepartmentString()).append(")\n");
        sb.append("      Has the following students: \n");
        if (students != null) {
            for (Student s : students) {
                sb.append("       * ").append(s.getFullName()).append("\n");
            }
        } else {
            sb.append("       ?");
        }

        return sb.toString();
    }

}
