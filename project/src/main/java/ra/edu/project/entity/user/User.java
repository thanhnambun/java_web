package ra.edu.project.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ra.edu.project.entity.candidate.Candidate;
import ra.edu.project.repository.candidate.CandidateRepository;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Enumerated(EnumType.STRING)
    private Status status;
    @PrePersist
    public void prePersist() {
        status = Status.ACTIVE;
        role = UserRole.CANDIDATE;
    }
}
