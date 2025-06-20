package ra.edu.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.edu.project.dto.ApplicationDTO;
import ra.edu.project.entity.application.Application;
import ra.edu.project.entity.application.Progress;
import ra.edu.project.entity.application.RequestResult;
import ra.edu.project.repository.application.ApplicationRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepo applicationRepository;

    @Transactional(readOnly = true)
    public List<ApplicationDTO> getAllActiveApplications(Progress progress, String result, int page, int pageSize) {
        List<Application> applications = applicationRepository.getAllActiveApplications(progress, result, page, pageSize);
        return applications.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public int countAllActiveApplications(Progress progress, String result) {
        return applicationRepository.countAllActiveApplications(progress, result);
    }

    @Transactional
    public void cancelApplication(int id, String reason) {
        applicationRepository.cancelApplication(id, reason);
    }

    @Transactional
    public void updateProgressToHandling(int id) {
        applicationRepository.updateProgressToHandling(id);
    }
    @Transactional
    public void updateProgress(int id,String progress) {
        applicationRepository.updateProgress(id,progress);
    }

    @Transactional
    public void updateInterviewInfo(int id, String interviewRequestDate, String interviewLink, String interviewTime) {
        applicationRepository.updateInterviewInfo(id, interviewRequestDate, interviewLink, interviewTime);
    }

    @Transactional
    public void updateInterviewResult(int id, String note, String result) {
        applicationRepository.updateInterviewResult(id, note, result);
    }

    @Transactional(readOnly = true)
    public List<ApplicationDTO> getApplicationsByCandidate(int candidateId, int page, int pageSize) {
        List<Application> applications = applicationRepository.getApplicationsByCandidate(candidateId, page, pageSize);
        return applications.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ApplicationDTO getApplicationDetailForCandidate(int candidateId, int applicationId) {
        Application app = applicationRepository.getApplicationDetailForCandidate(candidateId, applicationId);
        return app != null ? convertToDTO(app) : null;
    }

    @Transactional
    public void updateCandidateInterviewResponse(int applicationId, RequestResult result) {
        applicationRepository.updateCandidateInterviewResponse(applicationId, result);
    }

    @Transactional
    public void applyNewApplication(Application application) {
        applicationRepository.applyNewApplication(application);
    }
    public ApplicationDTO convertToDTO(Application application) {
        ApplicationDTO dto = new ApplicationDTO();
        dto.setId(application.getId());
        dto.setCandidateName(application.getCandidate().getName());
        dto.setRecruitmentPosition(application.getRecruitmentPosition().getName());

        if (application.getRecruitmentPosition().getTechnologies() != null) {
            List<String> techNames = application.getRecruitmentPosition().getTechnologies()
                    .stream()
                    .map(tech -> tech.getName())
                    .collect(Collectors.toList());
            dto.setTechnologyList(techNames);
        } else {
            dto.setTechnologyList(new ArrayList<>());
        }

        dto.setCvUrl(application.getCvUrl());
        dto.setProgress(application.getProgress());
        dto.setInterviewRequestDate(application.getInterviewRequestDate());
        dto.setInterviewRequestResult(application.getInterviewRequestResult());
        dto.setInterviewLink(application.getInterviewLink());
        dto.setInterviewTime(application.getInterviewTime());
        dto.setInterviewResultNote(application.getInterviewResultNote());
        dto.setDestroyReason(application.getDestroyReason());
        dto.setCreatedAt(application.getCreateAt());
        dto.setUpdatedAt(application.getUpdateAt());
        return dto;
    }
    @Transactional
    public int countApplicationsByCandidate(int candidateId) {
        return applicationRepository.countApplicationsByCandidate(candidateId);
    }
    @Transactional(readOnly = true)
    public Application findById(int id) {
        return applicationRepository.findById(id);
    }


    @Transactional
    public List<Application> searchApplications(String progress, String result, String keyword, int page, int size) {
        return  applicationRepository.searchApplications(progress,result,keyword,page, size);
    }
    @Transactional
    public int countSearchApplications(String progress, String result, String keyword) {
        return applicationRepository.countSearchApplications(progress,result,keyword);
    }
}
