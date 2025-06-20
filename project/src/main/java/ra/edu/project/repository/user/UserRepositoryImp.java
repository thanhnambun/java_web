package ra.edu.project.repository.user;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.edu.project.entity.user.User;

@Repository
public class UserRepositoryImp implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery(
                "FROM User WHERE username = :username AND password = :password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        return query.uniqueResult();
    }

    @Override
    public User findByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("FROM User WHERE username = :username", User.class);
        query.setParameter("username", username);
        return query.uniqueResult();
    }

    @Override
    public int save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
        return user.getId();
    }

    @Override
    public User getUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Candidate c join c.user u where u.id = :id ");
        return session.get(User.class, id);
    }

    @Override
    public void  update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }
}
