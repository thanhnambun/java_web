package com.data.ontap.repository;

import com.data.ontap.model.Course;
import com.data.ontap.repository.CourseRepo;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
@Repository
public class CourseRepoImpl implements CourseRepo {

    private final SessionFactory sessionFactory;

    public CourseRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Course> findAll(int page, int pageSize) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query<Course> query = session.createQuery("FROM Course", Course.class);
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            return query.getResultList();
        } catch (HibernateException e) {
            System.err.println("Error fetching all courses: " + e.getMessage());
            return Collections.emptyList();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public List<Course> findByName(String name, int page, int pageSize) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query<Course> query = session.createQuery(
                    "FROM Course WHERE name LIKE :nameParam", Course.class);
            query.setParameter("nameParam", "%" + name + "%");
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            return query.getResultList();
        } catch (HibernateException e) {
            System.err.println("Error searching courses by name: " + e.getMessage());
            return Collections.emptyList();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public Course findById(Long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.get(Course.class, id);
        } catch (HibernateException e) {
            System.err.println("Error finding course by id: " + e.getMessage());
            return null;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean save(Course course) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(course);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            System.err.println("Error saving course: " + e.getMessage());
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean delete(Long id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Course course = session.get(Course.class, id);
            if (course != null) {
                session.delete(course);
                tx.commit();
                return true;
            } else {
                System.err.println("Course with id=" + id + " not found.");
                tx.rollback();
                return false;
            }
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            System.err.println("Error deleting course: " + e.getMessage());
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean update(Course course) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.update(course);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            System.err.println("Error updating course: " + e.getMessage());
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public List<Course> findAllCous() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query<Course> query = session.createQuery(
                    "FROM Course ", Course.class);
            return query.getResultList();
        } catch (HibernateException e) {
            System.err.println("Error searching courses by name: " + e.getMessage());
            return Collections.emptyList();
        } finally {
            if (session != null) session.close();
        }
    }
}
