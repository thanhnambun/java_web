package com.data.ontap.repository;

import com.data.ontap.model.Course;
import com.data.ontap.model.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class StudentRepoImp implements StudentRepo {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Student> findAll(int page, int pageSize) {
        List<Student> students = new ArrayList<>();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query<Student> query = session.createQuery("FROM Student", Student.class);
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            students = query.getResultList();
        } catch (HibernateException e) {
            System.err.println("Error fetching all students: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return students;
    }


    @Override
    public List<Student> findByName(String nameIn, int page, int pageSize) {
        List<Student> students = new ArrayList<>();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query<Student> query = session.createQuery(
                    "FROM Student WHERE name LIKE :nameParam", Student.class);
            query.setParameter("nameParam", "%" + nameIn + "%");
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            students = query.getResultList();
        } catch (HibernateException e) {
            System.err.println("Error querying students by name: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return students;
    }



    @Override
    public Student findById(String idIn) {
        Student student = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query<Student> query = session.createQuery(
                    "FROM Student WHERE id = :idParam", Student.class);
            query.setParameter("idParam", idIn);
            student = query.uniqueResult();
        } catch (HibernateException e) {
            System.err.println("Error finding student by id: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return student;
    }


    @Override
    public boolean save(Student student) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(student);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            System.err.println("Error saving student: " + e.getMessage());
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean update(Student student) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.update(student);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            System.err.println("Error updating student: " + e.getMessage());
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean delete(String id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Student student = session.get(Student.class, id);
            if (student != null) {
                session.delete(student);
                tx.commit();
                return true;
            } else {
                tx.rollback();
                System.err.println("Student with id=" + id + " not found, nothing deleted.");
                return false;
            }
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            System.err.println("Error deleting student: " + e.getMessage());
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public Student findTopByOrderByIdDesc() {
        Student student = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query<Student> query = session.createQuery(
                    "FROM Student ORDER BY id DESC", Student.class);
            query.setMaxResults(1);
            student = query.uniqueResult();
        } catch (HibernateException e) {
            System.err.println("Error finding student end: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return student;
    }

    @Override
    public List<Student> findAllSt() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query<Student> query = session.createQuery(
                    "FROM Student ", Student.class);
            return query.getResultList();
        } catch (HibernateException e) {
            System.err.println("Error searching student by name: " + e.getMessage());
            return Collections.emptyList();
        } finally {
            if (session != null) session.close();
        }
    }
}
