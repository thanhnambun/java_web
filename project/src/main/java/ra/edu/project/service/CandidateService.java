package ra.edu.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.edu.project.dto.CandidateDTO;
import ra.edu.project.entity.candidate.Candidate;
import ra.edu.project.entity.candidate.Gender;
import ra.edu.project.entity.technology.Technology;
import ra.edu.project.entity.user.Status;
import ra.edu.project.entity.user.User;
import ra.edu.project.repository.candidate.CandidateRepositoryImp;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepositoryImp candidateRepositoryImp;

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    public List<CandidateDTO> getAllCandidates(int page, int pageSize) {
        List<Candidate> candidates = candidateRepositoryImp.getCandidates(page, pageSize);
        return candidates.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<CandidateDTO> filterCandidates(String name, Integer experience, Integer age, String gender, String technology,
                                               int pageNumber, int pageSize) {
        List<Object[]> results = candidateRepositoryImp.findCandidateWithAllConditions(
                name, experience, gender, technology, pageNumber, pageSize
        );

        Map<Integer, CandidateDTO> dtoMap = new HashMap<>();

        for (Object[] row : results) {
            int candidateId = (Integer) row[0];
            CandidateDTO dto = dtoMap.get(candidateId);

            if (dto == null) {
                dto = new CandidateDTO();
                dto.setId(candidateId);
                dto.setName((String) row[1]);
                dto.setEmail((String) row[2]);
                dto.setPhone((String) row[3]);
                dto.setExperience((Integer) row[4]);
                dto.setGender((Gender) row[5]);
                dto.setDescription((String) row[6]);
                dto.setDob((LocalDate) row[7]);

                Status status = (Status) row[8];
                if (status != null) {
                    dto.setStatus(status.name());
                }

                dto.setTechnologies(new ArrayList<>());
                dtoMap.put(candidateId, dto);
            }

            String techName = (String) row[9];
            dto.getTechnologies().add(techName);
        }

        List<CandidateDTO> finalList = new ArrayList<>(dtoMap.values());
        if (age != null) {
            finalList = finalList.stream()
                    .filter(dto -> {
                        if (dto.getDob() == null) return false;
                        int calculatedAge = Period.between(dto.getDob(), LocalDate.now()).getYears();
                        return calculatedAge == age;
                    })
                    .collect(Collectors.toList());
        }

        return finalList;
    }

    @Transactional
    public int countFilteredCandidates(String name, Integer experience, Integer age, String gender, String technology){
        return candidateRepositoryImp.countFilteredCandidates(name, experience, age, gender, technology);
    }


    @Transactional
    public boolean resetPassword(int userId, String newPassword) {
        return candidateRepositoryImp.resetPassword(userId, newPassword) > 0;
    }

    @Transactional(readOnly = true)
    public CandidateDTO getCandidateById(int userId) {
        Candidate candidate = candidateRepositoryImp.getCandidateById(userId);
        return candidate != null ? convertToDTO(candidate) : null;
    }

    @Transactional
    public boolean changeStatus(int userId, Status status) {
        return candidateRepositoryImp.changeStatus(userId, status) > 0;
    }

    @Transactional
    public Candidate getCandidateByUserid(int userId) {
        return candidateRepositoryImp.findById(userId);
    }
    @Transactional(readOnly = true)
    public CandidateDTO convertToDTO(Candidate candidate) {
        CandidateDTO dto = new CandidateDTO();
        dto.setId(candidate.getId());
        dto.setName(candidate.getName());
        dto.setEmail(candidate.getEmail());
        dto.setPhone(candidate.getPhone());
        dto.setExperience(candidate.getExperience());
        dto.setGender(candidate.getGender());
        dto.setDescription(candidate.getDescription());
        dto.setDob(candidate.getDob());

        if (candidate.getUser() != null && candidate.getUser().getStatus() != null) {
            dto.setStatus(candidate.getUser().getStatus().name());
        }

        List<String> technologyNames = candidate.getTechnologies()
                .stream()
                .map(Technology::getName)
                .collect(Collectors.toList());
        dto.setTechnologies(technologyNames);

        return dto;
    }
    @Transactional
    public boolean checkEmail(String email,int candidateId) {
        return candidateRepositoryImp.checkEmail(email,candidateId);
    }
    @Transactional
    public boolean checkPhone(String phone,int candidateId) {
        return candidateRepositoryImp.checkPhone(phone,candidateId);
    }
    @Transactional
    public Candidate findByEmailAndPhone(String email, String phone) {
        return candidateRepositoryImp.findByEmailAndPhone(email, phone);
    }
}
