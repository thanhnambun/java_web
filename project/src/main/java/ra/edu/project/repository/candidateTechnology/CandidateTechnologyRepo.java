package ra.edu.project.repository.candidateTechnology;

import java.util.List;

public interface CandidateTechnologyRepo {
    Boolean saveCandidateTechnology(int candidateId ,int technologyId );
    List<Integer> getTechnologyByCandidateId(int candidateId);
}
