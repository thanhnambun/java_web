package ra.edu.project.repository.candidate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.edu.project.entity.candidate.Candidate;
import ra.edu.project.entity.candidate.Gender;
import ra.edu.project.entity.user.Status;
import ra.edu.project.entity.user.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class CandidateRepositoryImp implements CandidateRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Candidate> getCandidates(int page, int pageSize) {
        Session session = sessionFactory.getCurrentSession();
        Query<Candidate> query = session.createQuery(
                "SELECT c FROM Candidate c LEFT JOIN FETCH c.user", Candidate.class
        );
        query.setFirstResult((page - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public int changePassword(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
        return 0;
    }

    @Override
    public List<Object[]> findCandidateWithAllConditions(String name, Integer experience, String gender, String technology,
                                                         int pageNumber, int pageSize) {
        Session session = sessionFactory.getCurrentSession();
        StringBuilder hql = new StringBuilder(
                "SELECT c.id, c.name, c.email, c.phone, c.experience, c.gender, c.description, c.dob, u.status, t.name " +
                        "FROM Candidate c " +
                        "JOIN c.technologies t " +
                        "JOIN c.user u " +
                        "WHERE 1=1 "
        );

        if (name != null && !name.isEmpty()) {
            hql.append("AND c.name LIKE :name ");
        }
        if (experience != null) {
            hql.append("AND c.experience = :experience ");
        }
        if (gender != null && !gender.isEmpty()) {
            hql.append("AND c.gender = :gender ");
        }
        if (technology != null && !technology.isEmpty()) {
            hql.append("AND t.name LIKE :technology ");
        }

        Query<Object[]> query = session.createQuery(hql.toString(), Object[].class);

        if (name != null && !name.isEmpty()) {
            query.setParameter("name", "%" + name + "%");
        }
        if (experience != null) {
            query.setParameter("experience", experience);
        }
        if (gender != null && !gender.isEmpty()) {
            try {
                Gender genderEnum = Gender.valueOf(gender.toUpperCase());
                query.setParameter("gender", genderEnum);
            } catch (IllegalArgumentException e) {
                return Collections.emptyList();
            }
        }
        if (technology != null && !technology.isEmpty()) {
            query.setParameter("technology", "%" + technology + "%");
        }

        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    @Override
    public int countFilteredCandidates(String name, Integer experience, Integer age, String gender, String technology) {
        Session session = sessionFactory.getCurrentSession();

        StringBuilder hql = new StringBuilder("SELECT COUNT(DISTINCT c.id) FROM Candidate c ");
        Map<String, Object> params = new HashMap<>();

        // Công nghệ thì phải JOIN
        if (technology != null && !technology.trim().isEmpty()) {
            hql.append("JOIN c.technologies t ");
        }

        hql.append("WHERE 1=1 ");

        if (name != null && !name.trim().isEmpty()) {
            hql.append("AND c.name LIKE :name ");
            params.put("name", "%" + name + "%");
        }
        if (experience != null) {
            hql.append("AND c.experience = :experience ");
            params.put("experience", experience);
        }
        if (gender != null && !gender.trim().isEmpty()) {
            hql.append("AND c.gender = :gender ");
            params.put("gender", Gender.valueOf(gender.toUpperCase()));
        }
        if (technology != null && !technology.trim().isEmpty()) {
            hql.append("AND t.name LIKE :technology ");
            params.put("technology", "%" + technology + "%");
        }

        Query<Long> query = session.createQuery(hql.toString(), Long.class);

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        Long count = query.uniqueResult();
        int totalCount = (count != null) ? count.intValue() : 0;

        if (age != null) {
            List<Candidate> allCandidates = session.createQuery("FROM Candidate", Candidate.class).getResultList();
            totalCount = (int) allCandidates.stream()
                    .filter(c -> {
                        if (c.getDob() == null) return false;
                        int calculatedAge = Period.between(c.getDob(), LocalDate.now()).getYears();
                        if (age != null && calculatedAge != age) return false;
                        if (name != null && !c.getName().contains(name)) return false;
                        if (experience != null && c.getExperience() != experience) return false;
                        if (gender != null && !c.getGender().name().equalsIgnoreCase(gender)) return false;
                        if (technology != null && (c.getTechnologies().stream().noneMatch(t -> t.getName().contains(technology)))) return false;
                        return true;
                    })
                    .count();
        }

        return totalCount;
    }



    @Override
    public int resetPassword(int userId, String newPassword) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(
                "UPDATE User u SET u.password = :newPassword WHERE u.id = :userId");
        query.setParameter("newPassword", newPassword);
        query.setParameter("userId", userId);
        return query.executeUpdate();
    }

    @Override
    public Candidate getCandidateById(int userId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Candidate.class, userId);
    }

    @Override
    public int changeStatus(int candidateId, Status status) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(
                "UPDATE User u SET u.status = :status WHERE u.id = " +
                        "(SELECT c.user.id FROM Candidate c WHERE c.id = :candidateId)");
        query.setParameter("status", status);
        query.setParameter("candidateId", candidateId);
        return query.executeUpdate();
    }

    @Override
    public int getTotalCandidatesCount() {
        Session session = sessionFactory.getCurrentSession();
        Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Candidate", Long.class);
        return query.uniqueResult().intValue();
    }

    @Override
    public int getTotalCandidatesByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query<Long> query = session.createQuery(
                "SELECT COUNT(*) FROM Candidate c WHERE c.name LIKE :name", Long.class);
        query.setParameter("name", "%" + name + "%");
        return query.uniqueResult().intValue();
    }

    @Override
    public int getTotalCandidatesByExperience(int experience) {
        Session session = sessionFactory.getCurrentSession();
        Query<Long> query = session.createQuery(
                "SELECT COUNT(*) FROM Candidate c WHERE c.experience = :experience", Long.class);
        query.setParameter("experience", experience);
        return query.uniqueResult().intValue();
    }

    @Override
    public int getTotalCandidatesByAge(int age) {
        Session session = sessionFactory.getCurrentSession();
        List<Candidate> all = session.createQuery("FROM Candidate", Candidate.class).getResultList();

        // Tính tuổi trong Java
        return (int) all.stream()
                .filter(c -> {
                    if (c.getDob() == null) return false;
                    int calculatedAge = Period.between(c.getDob(), LocalDate.now()).getYears();
                    return calculatedAge == age;
                })
                .count();
    }

    @Override
    public int getTotalCandidatesByGender(String gender) {
        Session session = sessionFactory.getCurrentSession();
        Gender genderEnum;
        try {
            genderEnum = Gender.valueOf(gender.toUpperCase());
        } catch (IllegalArgumentException e) {
            return 0;
        }
        Query<Long> query = session.createQuery(
                "SELECT COUNT(*) FROM Candidate c WHERE c.gender = :gender", Long.class);
        query.setParameter("gender", genderEnum);
        return query.uniqueResult().intValue();
    }

    @Override
    public int getTotalCandidatesByTechnology(String technology) {
        Session session = sessionFactory.getCurrentSession();
        Query<Long> query = session.createQuery(
                "SELECT COUNT(*) FROM Candidate c JOIN c.technologies t WHERE t.name LIKE :technology", Long.class);
        query.setParameter("technology", "%" + technology + "%");
        return query.uniqueResult().intValue();
    }

    @Override
    public void updateCandidate(Candidate candidate) {
        Session session = sessionFactory.getCurrentSession();
        session.update(candidate);
    }

    @Override
    public Candidate getCandidateByUserId(int id_in) {
        Session session = sessionFactory.getCurrentSession();
        Query<Candidate> query = session.createQuery(
                "FROM Candidate c WHERE c.user.id = :id_in", Candidate.class);
        query.setParameter("id_in", id_in);
        return query.uniqueResult();
    }


    @Override
    public boolean checkEmail(String email, int candidateId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Candidate> query = session.createQuery(
                "FROM Candidate WHERE email = :email AND id != :id", Candidate.class);
        query.setParameter("email", email);
        query.setParameter("id", candidateId);

        Candidate result = query.uniqueResult();
        return result != null; // true: email đã tồn tại (trùng)
    }

    @Override
    public boolean checkPhone(String phone, int candidateId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Candidate> query = session.createQuery(
                "FROM Candidate WHERE phone = :phone AND id != :id", Candidate.class);
        query.setParameter("phone", phone);
        query.setParameter("id", candidateId);

        Candidate result = query.uniqueResult();
        return result != null; // true: phone đã tồn tại (trùng)
    }


    @Override
    public void save(Candidate candidate) {
        Session session = sessionFactory.getCurrentSession();
        session.save(candidate);
    }

    @Override
    public Candidate findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Candidate> query = session.createQuery(
                "SELECT c FROM Candidate c LEFT JOIN FETCH c.technologies WHERE c.id = :id", Candidate.class
        );
        query.setParameter("id", id);
        return query.uniqueResult();
    }

}
