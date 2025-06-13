package ra.edu.project.repository.technology;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.edu.project.entity.technology.Technology;

import java.util.List;

@Repository
public class TechnologyRepositoryImp implements TechnologyRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean saveTechnology(Technology technology) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.save(technology);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteTechnology(Technology technology) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.delete(technology);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateTechnology(Technology technology) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.merge(technology);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Technology> findAll(int page, int pageSize) {
        Session session = sessionFactory.getCurrentSession();
        Query<Technology> query = session.createQuery("FROM Technology", Technology.class);
        query.setFirstResult((page - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public Technology findTechnologyById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Technology.class, id);
    }

    @Override
    public List<Technology> findTechnologyByName(String name, int page, int pageSize) {
        Session session = sessionFactory.getCurrentSession();
        Query<Technology> query = session.createQuery(
                "FROM Technology t WHERE t.name LIKE :name", Technology.class);
        query.setParameter("name", "%" + name + "%");
        query.setFirstResult((page - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Override
    public Technology findByExactName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query<Technology> query = session.createQuery(
                " FROM Technology t WHERE t.name = :name", Technology.class);
        query.setParameter("name", name);
        List<Technology> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public int checkTechnologyByCandidate(Technology technology) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT COUNT(c.id) FROM Candidate c join c.technologies t  WHERE t.id = :techId";
        Long count = session.createQuery(hql, Long.class)
                .setParameter("techId", technology.getId())
                .uniqueResult();
        return count.intValue();
    }

    @Override
    public int checkTechnologyByRecruitmentPosition(Technology technology) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT COUNT(r.id) FROM RecruitmentPosition r join r.technologies t  WHERE t.id = :techId";
        Long count = session.createQuery(hql, Long.class)
                .setParameter("techId", technology.getId())
                .uniqueResult();
        return count.intValue();
    }
}
