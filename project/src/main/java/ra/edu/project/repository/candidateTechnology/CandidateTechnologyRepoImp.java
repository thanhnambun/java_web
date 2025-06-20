package ra.edu.project.repository.candidateTechnology;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CandidateTechnologyRepoImp implements CandidateTechnologyRepo {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Boolean saveCandidateTechnology(int candidateId, int technologyId) {
        try {
            Session session = sessionFactory.getCurrentSession();

            String sql = "INSERT INTO candidate_technology(candidate_id, technology_id) VALUES (:candidateId, :technologyId)";
            session.createNativeQuery(sql)
                    .setParameter("candidateId", candidateId)
                    .setParameter("technologyId", technologyId)
                    .executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
