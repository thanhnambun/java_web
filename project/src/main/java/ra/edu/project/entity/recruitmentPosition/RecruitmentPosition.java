package ra.edu.project.entity.recruitmentPosition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ra.edu.project.entity.technology.Technology;

import javax.persistence.*;
import java.time.LocalDate;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private double minSalary;
    private double maxSalary;
    private int minExperience;
    private LocalDate createdDate;
    private LocalDate expiredDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToMany
    @JoinTable(
            name = "recruitmentPositions_technology",
            joinColumns = @JoinColumn(name = "recruitment_position_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id")
    )
    private List<Technology> technologies;

    @PrePersist
    public void prePersist()
    {
        createdDate = LocalDate.now();
        status = Status.ACTIVE;
    }


}