package ra.edu.project.repository.candidate;

import ra.edu.project.entity.candidate.Candidate;
import ra.edu.project.entity.user.Status;
import ra.edu.project.entity.user.User;

import java.util.List;

public interface CandidateRepository {
    boolean checkEmail(String email,int candidateId);
    boolean checkPhone(String phone,int candidateId);
    void save(Candidate candidate);
    Candidate findById(int id);
    List<Candidate> getCandidates(int page, int pageSize);
    int changePassword(User user);

    List<Object[]> findCandidateWithAllConditions(String name, Integer experience, String gender, String technology,
                                                  int pageNumber, int pageSize);
    int countFilteredCandidates(String name, Integer experience, Integer age, String gender, String technology) ;

    int resetPassword(int userId, String newPassword);

    Candidate getCandidateById(int userId);

    int changeStatus(int candidateId, Status status);

    int getTotalCandidatesCount(); // Thêm để lấy tổng số ứng viên

    int getTotalCandidatesByName(String name); // Thêm để lấy tổng số ứng viên theo tên

    int getTotalCandidatesByExperience(int experience); // Thêm để lấy tổng số ứng viên theo kinh nghiệm

    int getTotalCandidatesByAge(int age); // Thêm để lấy tổng số ứng viên theo tuổi

    int getTotalCandidatesByGender(String gender); // Thêm để lấy tổng số ứng viên theo giới tính

    int getTotalCandidatesByTechnology(String technology); // Thêm để lấy tổng số ứng viên theo công nghệ
    void updateCandidate(Candidate candidate);
    Candidate getCandidateByUserId(int id );
    Candidate findByEmailAndPhone(String email, String phone);
}
