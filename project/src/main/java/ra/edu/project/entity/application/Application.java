package ra.edu.project.entity.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ra.edu.project.entity.candidate.Candidate;
import ra.edu.project.entity.recruitmentPosition.RecruitmentPosition;

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
    private String cvUrl;
    @Enumerated(EnumType.STRING)
    private Progress progress;
    private LocalDateTime interviewRequestDate;
    @Enumerated(EnumType.STRING)
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
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
    @OneToOne
    @JoinColumn(name = "recruitmentPosition_id")
    private RecruitmentPosition recruitmentPosition;


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