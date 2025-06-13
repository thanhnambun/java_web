package ra.edu.project.entity.technology;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ra.edu.project.entity.application.Application;
import ra.edu.project.entity.candidate.Candidate;
import ra.edu.project.entity.recruitmentPosition.RecruitmentPosition;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Technology {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Status status;
    @ManyToMany(mappedBy = "technologies")
    private List<Candidate>candidates;
    @ManyToMany
    @JoinTable(
            name = "recruitmentPositions_technology",
            joinColumns = @JoinColumn(name = "technology_id"),
            inverseJoinColumns = @JoinColumn(name = "recruitment_position_id")
    )
    private List<RecruitmentPosition> recruitmentPositions;
    @PrePersist
    public void prePersist() {
        status = Status.ACTIVE;
    }
}