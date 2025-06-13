package ra.edu.project.entity.candidate;
import lombok.Getter;
import lombok.Setter;
import ra.edu.project.entity.application.Application;
import ra.edu.project.entity.technology.Technology;
import ra.edu.project.entity.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String phone;
    private int experience;
    private Gender gender;
    private String description;
    private LocalDate dob;

    @OneToMany(mappedBy = "candidate")
    private List<Application> applications;
    @ManyToMany
    @JoinTable(
            name = "candidate_technology",
            joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id")
    )
    private List<Technology> technologies;
}