package ra.edu.project.repository.application;


import ra.edu.project.entity.application.Application;
import ra.edu.project.entity.application.Progress;
import ra.edu.project.entity.application.RequestResult;

import java.util.List;

public interface ApplicationRepo {

    List<Application> getAllActiveApplications(Progress progress, String result, int page, int pageSize);

    int countAllActiveApplications(Progress progress, String result);

    Application findById(int id);

    int cancelApplication(int id, String destroyReason);

    int updateProgressToHandling(int id);

    int updateProgress(int id,String progress);

    int updateInterviewInfo(int id, String interviewRequestDate, String interviewLink, String interviewTime);

    int updateInterviewResult(int id, String interviewResultNote, String interviewResult);

    List<Application> getApplicationsByCandidate(int candidateId, int page, int pageSize);

    int countApplicationsByCandidate(int candidateId);

    Application getApplicationDetailForCandidate(int candidateId, int applicationId);

    int updateCandidateInterviewResponse(int applicationId, RequestResult requestResult);



    void applyNewApplication(Application application);
    List<Application> searchApplications(String progress, String result, String keyword, int page, int size);
    int countSearchApplications(String progress, String result, String keyword);
}

