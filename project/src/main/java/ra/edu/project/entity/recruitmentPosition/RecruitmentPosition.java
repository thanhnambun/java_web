package ra.edu.project.entity.recruitmentPosition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ra.edu.project.entity.technology.Technology;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import java.time.LocalDate;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentPosition {
    @Id
    private int id;
    private String name;
    private String description;
    private double minSalary;
    private double maxSalary;
    private int minExperience;
    private LocalDate createdDate;
    private LocalDate expiredDate;
    private Status status;
    @OneToMany
    private List<Technology> technologies;

    @PrePersist
    public void prePersist()
    {
        createdDate = LocalDate.now();
        status = Status.ACTIVE;
    }


}