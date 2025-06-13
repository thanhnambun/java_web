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
import ra.edu.project.entity.user.User;
import ra.edu.project.repository.candidate.CandidateRepositoryImp;
import ra.edu.project.repository.user.UserRepositoryImp;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private ModelMapper modelMapper;

    @Autowired
    private Validator validator;

    @Transactional
    public List<String> register(RegistrationDTO registrationDTO) {
        List<String> errors = new ArrayList<>();

        // Validate UserDTO
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
        candidateRepositoryImp.save(candidate);

        return null;
    }

    @Transactional
    public User login(String username, String password, HttpServletResponse response, boolean rememberMe) {
        User user = userRepositoryImp.findByUsername(username);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            // Lưu username và role vào cookie
            Cookie usernameCookie = new Cookie("username", user.getUsername());
            Cookie roleCookie = new Cookie("role", user.getRole().toString());
            usernameCookie.setPath("/");
            roleCookie.setPath("/");

            if (rememberMe) {
                // Lưu trong 7 ngày
                usernameCookie.setMaxAge(1 * 24 * 60 * 60);
                roleCookie.setMaxAge(1 * 24 * 60 * 60);
            } else {
                usernameCookie.setMaxAge(-1);
                roleCookie.setMaxAge(-1);
            }

            response.addCookie(usernameCookie);
            response.addCookie(roleCookie);

            return user;
        }
        return null;
    }




    public void logout(HttpServletResponse response) {
        Cookie usernameCookie = new Cookie("username", null);
        Cookie roleCookie = new Cookie("role", null);
        usernameCookie.setMaxAge(0);
        roleCookie.setMaxAge(0);
        usernameCookie.setPath("/");
        roleCookie.setPath("/");

        response.addCookie(usernameCookie);
        response.addCookie(roleCookie);
    }

    public String getCurrentUsername(HttpServletRequest request) {
        for (Cookie cookie : request.getCookies()) {
            if ("username".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

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
    public int updateUserStatus(int id, String status) {
        return userRepositoryImp.updateStatus(id, status);
    }
}
