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
    private Status status;
    @ManyToMany(mappedBy = "recruitmentPositions")
    private List<Technology> technologies;

    @PrePersist
    public void prePersist()
    {
        createdDate = LocalDate.now();
        status = Status.ACTIVE;
    }


}