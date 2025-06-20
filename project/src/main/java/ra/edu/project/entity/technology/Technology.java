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
    @Enumerated(EnumType.STRING)
    private Status status;
    //    @ManyToMany(mappedBy = "technologies")
//    private List<Candidate>candidates;
    @ManyToMany(mappedBy = "technologies")
    private List<RecruitmentPosition> recruitmentPositions;

    @PrePersist
    public void prePersist() {
        status = Status.ACTIVE;
    }
}