package ra.edu.project.service;

import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.edu.project.dto.RegistrationDTO;
import ra.edu.project.dto.UserDTO;
import ra.edu.project.dto.CandidateDTO;
import ra.edu.project.entity.candidate.Candidate;
import ra.edu.project.entity.technology.Technology;
import ra.edu.project.entity.user.Status;
import ra.edu.project.entity.user.User;
import ra.edu.project.repository.candidate.CandidateRepositoryImp;
import ra.edu.project.repository.technology.TechnologyRepositoryImp;
import ra.edu.project.repository.user.UserRepositoryImp;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepositoryImp userRepositoryImp;

    @Autowired
    private CandidateRepositoryImp candidateRepositoryImp;

    @Autowired
    private TechnologyService technologyService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Validator validator;

    @Transactional
    public List<String> register(RegistrationDTO registrationDTO) {
        List<String> errors = new ArrayList<>();

        Set<ConstraintViolation<UserDTO>> userViolations = validator.validate(registrationDTO.getUserDTO());
        userViolations.forEach(v -> errors.add(v.getMessage()));

        Set<ConstraintViolation<CandidateDTO>> candidateViolations = validator.validate(registrationDTO.getCandidateDTO());
        candidateViolations.forEach(v -> errors.add(v.getMessage()));

        User existingUser = userRepositoryImp.findByUsername(registrationDTO.getUserDTO().getUsername());
        if (existingUser != null) {
            errors.add("Username already exists");
        }

        if (!errors.isEmpty()) {
            return errors;
        }
        User user = modelMapper.map(registrationDTO.getUserDTO(), User.class);
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        userRepositoryImp.save(user);

        Candidate candidate = modelMapper.map(registrationDTO.getCandidateDTO(), Candidate.class);
        candidate.setId(user.getId());
        candidate.setUser(user);

        List<String> techNames = registrationDTO.getCandidateDTO().getTechnologies();
        List<Technology> techList = new ArrayList<>();
        for (String techName : techNames) {
            Integer techId = technologyService.findByExactName(techName);
            Technology technology = new Technology();
            technology.setId(techId);
            techList.add(technology);
        }
        candidate.setTechnologies(techList);
        candidateRepositoryImp.save(candidate);

        return null;
    }

    @Transactional
    public User login(String username, String password,
                      HttpServletRequest request,
                      HttpServletResponse response,
                      boolean rememberMe) {

        User user = userRepositoryImp.findByUsername(username);

        if (user != null) {
            if (user.getStatus().name().equalsIgnoreCase(Status.INACTIVE.name())) {
                throw new RuntimeException("Tài khoản của bạn đã bị khóa.");
            }

            if (BCrypt.checkpw(password, user.getPassword())) {
                // Lưu cookie username (rememberMe)
                Cookie usernameCookie = new Cookie("username", user.getUsername());
                usernameCookie.setPath("/");

                if (rememberMe) {
                    usernameCookie.setMaxAge(7 * 24 * 60 * 60); // 7 ngày
                } else {
                    usernameCookie.setMaxAge(-1);
                }
                response.addCookie(usernameCookie);

                HttpSession session = request.getSession(true);
                session.setAttribute("role", user.getRole().name());
                session.setAttribute("user", user);

                return user;
            }
        }
        return null;
    }


    @Transactional
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        // Xóa cookie username
        Cookie usernameCookie = new Cookie("username", null);
        usernameCookie.setMaxAge(0);
        usernameCookie.setPath("/");
        response.addCookie(usernameCookie);

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    @Transactional
    public String getCurrentUsername(HttpServletRequest request) {
        for (Cookie cookie : request.getCookies()) {
            if ("username".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
    @Transactional
    public String getCurrentUserRole(HttpServletRequest request) {
        for (Cookie cookie : request.getCookies()) {
            if ("role".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    @Transactional
    public User getUserById(int id) {
        return userRepositoryImp.getUserById(id);
    }
    @Transactional
    public User getUserByUsername(String username) {
        return userRepositoryImp.findByUsername(username);
    }
}
