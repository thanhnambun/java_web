package ra.edu.project.entity.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ra.edu.project.entity.candidate.Candidate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Application  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int candidateId;
    private int recruitmentPositionId;
    private String cvUrl;
    private Progress progress;
    private LocalDateTime interviewRequestDate;
    private RequestResult interviewRequestResult;
    private String interviewLink;
    private LocalDateTime interviewTime;
    private String interviewResult;
    private String interviewResultNote;
    private LocalDateTime destroyAt;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String destroyReason;
    @ManyToOne
    private Candidate candidate;


    @PrePersist
    public void prePersist() {
        createAt = LocalDateTime.now();
        updateAt = LocalDateTime.now();
        progress = Progress.PENDING;
    }

    @PreUpdate
    public void preUpdate() {
        updateAt = LocalDateTime.now();
    }

}