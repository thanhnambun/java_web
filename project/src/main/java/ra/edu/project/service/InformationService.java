package ra.edu.project.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.edu.project.dto.CandidateDTO;
import ra.edu.project.entity.candidate.Candidate;
import ra.edu.project.entity.technology.Technology;
import ra.edu.project.entity.user.User;
import ra.edu.project.repository.candidate.CandidateRepositoryImp;
import ra.edu.project.repository.user.UserRepository;
import ra.edu.project.repository.user.UserRepositoryImp;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class InformationService {
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepositoryImp userRepositoryImp;
    @Autowired
    private TechnologyService technologyService;
    @Autowired
    private CandidateRepositoryImp candidateRepositoryImp;
    @Autowired
    private Validator validator;

    @Transactional
    public List<String> updateCandidateInfo(CandidateDTO candidateDTO, HttpServletRequest request) {
        List<String> errors = new ArrayList<>();

        Set<ConstraintViolation<CandidateDTO>> violations = validator.validate(candidateDTO);
        violations.forEach(v -> errors.add(v.getMessage()));

        if (!errors.isEmpty()) return errors;

        String username = userService.getCurrentUsername(request);
        if (username == null) {
            errors.add("Chưa đăng nhập.");
            return errors;
        }

        User user = userRepositoryImp.findByUsername(username);
        Candidate candidate = candidateRepositoryImp.getCandidateByUserId(user.getId());

        if (candidate == null) {
            errors.add("Không tìm thấy ứng viên.");
            return errors;
        }

        if (candidateRepositoryImp.checkEmail(candidateDTO.getEmail(), candidate.getId())) {
            errors.add("Email đã được sử dụng bởi ứng viên khác.");
        }

        if (candidateRepositoryImp.checkPhone(candidateDTO.getPhone(), candidate.getId())) {
            errors.add("Số điện thoại đã được sử dụng bởi ứng viên khác.");
        }


        if (!errors.isEmpty()) return errors;

        candidate.setName(candidateDTO.getName());
        candidate.setEmail(candidateDTO.getEmail());
        candidate.setPhone(candidateDTO.getPhone());
        candidate.setExperience(candidateDTO.getExperience());
        candidate.setGender(candidateDTO.getGender());
        candidate.setDescription(candidateDTO.getDescription());
        candidate.setDob(candidateDTO.getDob());

        List<Technology> techList = new ArrayList<>();
        for (String techName : candidateDTO.getTechnologies()) {
            Integer techId = technologyService.findByExactName(techName);
            if (techId != null) {
                Technology technology = new Technology();
                technology.setId(techId);
                techList.add(technology);
            } else {
                errors.add("Không tìm thấy công nghệ: " + techName);
            }
        }
        candidate.setTechnologies(techList);

        candidateRepositoryImp.updateCandidate(candidate);

        return errors.isEmpty() ? null : errors;
    }

    @Transactional
    public int changePassword(int userId, String oldPassword, String newPassword, String phone) {
        User user = userRepositoryImp.getUserById(userId);
        if (user == null) {
            return -1;
        }

        Candidate candidate = candidateRepositoryImp.getCandidateByUserId(userId);
        if (candidate == null) {
            return -2;
        }

        if (!BCrypt.checkpw(oldPassword, user.getPassword())) {
            return -3;
        }

        // Kiểm tra số điện thoại có đúng của candidate này không
        if (!candidate.getPhone().equals(phone)) {
            return -4; // Số điện thoại không đúng
        }

        // Hash và cập nhật mật khẩu mới
        String hashedNewPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        user.setPassword(hashedNewPassword);

        // Cập nhật vào DB
        userRepositoryImp.update(user);

        return 1; // Đổi mật khẩu thành công
    }


}
