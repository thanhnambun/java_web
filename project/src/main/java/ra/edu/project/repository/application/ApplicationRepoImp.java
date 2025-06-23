package ra.edu.project.repository.application;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.edu.project.entity.application.Application;
import ra.edu.project.entity.application.Progress;
import ra.edu.project.entity.application.RequestResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class ApplicationRepoImp implements ApplicationRepo {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Application> getAllActiveApplications(Progress progress, String result, int page, int pageSize) {
        Session session = sessionFactory.getCurrentSession();
        StringBuilder hql = new StringBuilder("FROM Application a WHERE a.destroyAt IS NULL ");

        if (progress != null) {
            hql.append("AND a.progress = :progress ");
        }
        if (result != null && !result.isEmpty()) {
            hql.append("AND a.interviewResult = :result ");
        }

        Query<Application> query = session.createQuery(hql.toString(), Application.class);

        if (progress != null) {
            query.setParameter("progress", progress);
        }
        if (result != null && !result.isEmpty()) {
            query.setParameter("result", result);
        }


        query.setFirstResult(page);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }


    @Override
    public int countAllActiveApplications(Progress progress, String result) {
        Session session = sessionFactory.getCurrentSession();
        StringBuilder hql = new StringBuilder("SELECT COUNT(a.id) FROM Application a WHERE a.destroyAt IS NULL ");

        if (progress != null) {
            hql.append("AND a.progress = :progress ");
        }
        if (result != null && !result.isEmpty()) {
            hql.append("AND a.interviewResult = :result ");
        }

        Query<Long> query = session.createQuery(hql.toString(), Long.class);

        if (progress != null) {
            query.setParameter("progress", progress);
        }
        if (result != null && !result.isEmpty()) {
            query.setParameter("result", result);
        }

        return query.uniqueResult().intValue();
    }

    @Override
    public Application findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Application.class, id);
    }

    @Override
    public int cancelApplication(int id, String destroyReason) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE Application a\n" +
                "        SET a.destroyAt = :destroyAt,\n" +
                "            a.destroyReason = :reason,\n" +
                "            a.progress = :progress\n" +
                "        WHERE a.id = :id");
        query.setParameter("destroyAt", LocalDateTime.now());
        query.setParameter("reason", destroyReason);
        query.setParameter("progress", Progress.CANCEL);
        query.setParameter("id", id);
        return query.executeUpdate();
    }

    @Override
    public int updateProgressToHandling(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE Application a SET a.progress = :progress WHERE a.id = :id AND a.progress = :pending");
        query.setParameter("progress", Progress.HANDLING);
        query.setParameter("pending", Progress.PENDING);
        query.setParameter("id", id);
        return query.executeUpdate();
    }

    @Override
    public int updateProgress(int id, String progress) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE Application a SET a.progress = :progress WHERE a.id = :id ");
        query.setParameter("progress", progress);
        query.setParameter("id", id);
        return query.executeUpdate();
    }

    @Override
    public int updateInterviewInfo(int id, LocalDateTime interviewRequestDate, String interviewLink, LocalDateTime interviewTime) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(
                "UPDATE Application a SET a.interviewRequestDate = :interviewRequestDate, " +
                        "a.interviewLink = :interviewLink, " +
                        "a.interviewTime = :interviewTime, " +
                        "a.progress = :progress WHERE a.id = :id"
        );

        query.setParameter("interviewRequestDate", interviewRequestDate);
        query.setParameter("interviewLink", interviewLink);
        query.setParameter("interviewTime", interviewTime);
        query.setParameter("progress", Progress.INTERVIEWING);
        query.setParameter("id", id);

        return query.executeUpdate();
    }




    @Override
    public int updateInterviewResult(int id, String interviewResultNote, String interviewResult) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(
                "UPDATE Application a SET a.interviewResultNote = :note, a.interviewResult = :result, " +
                        "a.progress = :progress WHERE a.id = :id"
        );
        query.setParameter("note", interviewResultNote);
        query.setParameter("result", interviewResult);
        query.setParameter("progress", Progress.DONE);
        query.setParameter("id", id);
        return query.executeUpdate();
    }

    @Override
    public List<Application> getApplicationsByCandidate(int candidateId, int page, int pageSize) {
        Session session = sessionFactory.getCurrentSession();
        Query<Application> query = session.createQuery(
                "FROM Application a WHERE a.candidate.id = :candidateId", Application.class);
        query.setParameter("candidateId", candidateId);
        int firstResult = (page <= 0 ? 0 : (page - 1) * pageSize);
        query.setFirstResult(firstResult);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public int countApplicationsByCandidate(int candidateId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Long> query = session.createQuery(
                "SELECT COUNT(a.id) FROM Application a WHERE a.candidate.id = :candidateId", Long.class);
        query.setParameter("candidateId", candidateId);
        return query.uniqueResult().intValue();
    }

    @Override
    public Application getApplicationDetailForCandidate(int candidateId, int applicationId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Application> query = session.createQuery(
                "FROM Application a WHERE a.id = :applicationId AND a.candidate.id = :candidateId", Application.class);
        query.setParameter("applicationId", applicationId);
        query.setParameter("candidateId", candidateId);
        return query.uniqueResult();
    }

    @Override
    public int updateCandidateInterviewResponse(int applicationId, RequestResult requestResult) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE Application a SET a.interviewRequestResult = :result WHERE a.id = :id");
        query.setParameter("result", requestResult);
        query.setParameter("id", applicationId);
        return query.executeUpdate();
    }

    @Override
    public void applyNewApplication(Application application) {
        Session session = sessionFactory.getCurrentSession();
        session.save(application);
    }
    @Override
    public List<Application> searchApplications(String progress, String result, String keyword, int page, int size) {
        Session session = sessionFactory.getCurrentSession();
        StringBuilder hql = new StringBuilder("SELECT DISTINCT a FROM Application a " +
                "JOIN FETCH a.recruitmentPosition rp " +
                "LEFT JOIN FETCH rp.technologies " +
                "WHERE 1=1 ");

        if (progress != null && !progress.isEmpty()) {
            hql.append(" AND a.progress = :progress");
        }
        if (result != null && !result.isEmpty()) {
            hql.append(" AND a.result = :result");
        }
        if (keyword != null && !keyword.isEmpty()) {
            hql.append(" AND (a.candidate.name LIKE :keyword OR rp.name LIKE :keyword)");
        }

        Query<Application> query = session.createQuery(hql.toString(), Application.class);

        if (progress != null && !progress.isEmpty()) {
            query.setParameter("progress", Progress.valueOf(progress));
        }

        if (result != null && !result.isEmpty()) {
            query.setParameter("result", RequestResult.valueOf(result));
        }

        if (keyword != null && !keyword.isEmpty()) {
            query.setParameter("keyword", "%" + keyword + "%");
        }

        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);

        return query.list();
    }

    @Override
    public int countSearchApplications(String progress, String result, String keyword) {
        Session session = sessionFactory.getCurrentSession();
        StringBuilder hql = new StringBuilder("SELECT COUNT(a) FROM Application a WHERE 1=1");

        if (progress != null && !progress.isEmpty()) {
            hql.append(" AND a.progress = :progress");
        }
        if (result != null && !result.isEmpty()) {
            hql.append(" AND a.result = :result");
        }
        if (keyword != null && !keyword.isEmpty()) {
            hql.append(" AND (a.candidate.name LIKE :keyword OR a.recruitmentPosition.name LIKE :keyword)");
        }

        Query<Long> query = session.createQuery(hql.toString(), Long.class);

        if (progress != null && !progress.isEmpty()) {
            query.setParameter("progress", Progress.valueOf(progress));
        }

        if (result != null && !result.isEmpty()) {
            query.setParameter("result", RequestResult.valueOf(result));
        }

        if (keyword != null && !keyword.isEmpty()) {
            query.setParameter("keyword", "%" + keyword + "%");
        }

        Long count = query.uniqueResult();
        return count != null ? count.intValue() : 0;
    }

}
