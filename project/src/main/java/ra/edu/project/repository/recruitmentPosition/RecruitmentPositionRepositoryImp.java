package ra.edu.project.repository.recruitmentPosition;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.edu.project.entity.recruitmentPosition.RecruitmentPosition;
import ra.edu.project.entity.recruitmentPosition.Status;

import java.util.List;

@Repository
public class RecruitmentPositionRepositoryImp implements RecruitmentPositionRepository {

    @Autowired
    private SessionFactory sessionFactory;

    // Lấy danh sách RecruitmentPosition ACTIVE - KHÔNG FETCH
    public List<RecruitmentPosition> findAllActive(int page, int size) {
        try (Session session = sessionFactory.openSession()) {
            Query<RecruitmentPosition> query = session.createQuery(
                    "FROM RecruitmentPosition rp WHERE rp.status = :status",
                    RecruitmentPosition.class);
            query.setParameter("status", Status.ACTIVE);
            query.setFirstResult(page * size);
            query.setMaxResults(size);
            List<RecruitmentPosition> positions = query.list();

            // Lazy fetch technologies (tránh Join Fetch warning)
            positions.forEach(pos -> pos.getTechnologies().size());
            return positions;
        }
    }

    // Lấy toàn bộ - Không fetch
    public List<RecruitmentPosition> findAll(int page, int size) {
        try (Session session = sessionFactory.openSession()) {
            Query<RecruitmentPosition> query = session.createQuery(
                    "FROM RecruitmentPosition",
                    RecruitmentPosition.class);
            query.setFirstResult(page * size);
            query.setMaxResults(size);
            List<RecruitmentPosition> positions = query.list();

            // Lazy fetch technologies
            positions.forEach(pos -> pos.getTechnologies().size());
            return positions;
        }
    }

    public long countActive() {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery(
                    "SELECT COUNT(*) FROM RecruitmentPosition WHERE status = :status",
                    Long.class);
            query.setParameter("status", Status.ACTIVE);
            return query.uniqueResult();
        }
    }

    public long countAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery(
                    "SELECT COUNT(*) FROM RecruitmentPosition",
                    Long.class);
            return query.uniqueResult();
        }
    }

    public RecruitmentPosition findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            RecruitmentPosition position = session.get(RecruitmentPosition.class, id);
            if (position != null) {
                position.getTechnologies().size(); // Lazy fetch
            }
            return position;
        }
    }

    public void save(RecruitmentPosition position) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            if (position.getId() == 0) {
                session.save(position);
            } else {
                session.update(position);
            }
            session.getTransaction().commit();
        }
    }

    public void updateStatus(int id, Status status) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            RecruitmentPosition position = session.get(RecruitmentPosition.class, id);
            if (position != null) {
                position.setStatus(status);
                session.update(position);
            }
            session.getTransaction().commit();
        }
    }

    public boolean existsByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery(
                    "SELECT COUNT(*) FROM RecruitmentPosition WHERE name = :name",
                    Long.class);
            query.setParameter("name", name);
            return query.uniqueResult() > 0;
        }
    }

    public List<RecruitmentPosition> searchByName(String keyword, int page, int size) {
        try (Session session = sessionFactory.openSession()) {
            Query<RecruitmentPosition> query = session.createQuery(
                    "FROM RecruitmentPosition rp WHERE lower(rp.name) LIKE :kw AND rp.status = :status",
                    RecruitmentPosition.class);
            query.setParameter("kw", "%" + keyword.toLowerCase() + "%");
            query.setParameter("status", Status.ACTIVE);
            query.setFirstResult(page * size);
            query.setMaxResults(size);
            List<RecruitmentPosition> positions = query.list();

            // Lazy fetch technologies
            positions.forEach(pos -> pos.getTechnologies().size());
            return positions;
        }
    }

    public long countByName(String keyword) {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery(
                    "SELECT COUNT(*) FROM RecruitmentPosition WHERE lower(name) LIKE :kw AND status = :status",
                    Long.class);
            query.setParameter("kw", "%" + keyword.toLowerCase() + "%");
            query.setParameter("status", Status.ACTIVE);
            return query.uniqueResult();
        }
    }

}
