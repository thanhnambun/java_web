package ra.edu.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.edu.project.repository.candidateTechnology.CandidateTechnologyRepo;


@Service
public class CandidateTechnologyService {
    @Autowired
    private CandidateTechnologyRepo candidateTechnologyRepo;
    @Transactional
    public boolean save(int candidateId, int technologyId){
        return candidateTechnologyRepo.saveCandidateTechnology(candidateId, technologyId);
    }
}
