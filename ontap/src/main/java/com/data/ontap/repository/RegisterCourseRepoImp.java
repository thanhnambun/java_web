package com.data.ontap.repository;

import com.data.ontap.model.Course;
import com.data.ontap.model.RegisterCourse;
import com.data.ontap.model.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class RegisterCourseRepoImp implements RegisterCourseRepo {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private CourseRepo courseRepo;

    @Override
    public List<RegisterCourse> findAll(int page, int pageSize) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.createQuery("FROM RegisterCourse", RegisterCourse.class)
                    .setFirstResult((page - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .list();
        } catch (HibernateException e) {
            System.err.println("Error fetching paged registrations: " + e.getMessage());
            return Collections.emptyList();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public List<Course> findByIdCourse(Long idCourse) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.createQuery(
                            "SELECT rc.course FROM RegisterCourse rc WHERE rc.course.id = :courseId", Course.class)
                    .setParameter("courseId", idCourse)
                    .list();
        } catch (HibernateException e) {
            System.err.println("Error finding courses by registration: " + e.getMessage());
            return Collections.emptyList();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public List<Student> findByIdStudent(String idStudent) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.createQuery(
                            "SELECT rc.student FROM RegisterCourse rc WHERE rc.student.id = :studentId", Student.class)
                    .setParameter("studentId", idStudent)
                    .list();
        } catch (HibernateException e) {
            System.err.println("Error finding students by registration: " + e.getMessage());
            return Collections.emptyList();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean addCourse(Long idCourse, String idStudent) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            RegisterCourse rc = new RegisterCourse();
            Course courses = courseRepo.findById(idCourse);
            Student student = studentRepo.findById(idStudent);
            rc.setStudent(student);
            rc.setCourse(courses);

            session.save(rc);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            System.err.println("Error adding registration: " + e.getMessage());
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean updateCourse(Long idCourse, String idStudent) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            RegisterCourse rc = session.createQuery(
                            "FROM RegisterCourse rc WHERE rc.course.id = :courseId AND rc.student.id = :studentId", RegisterCourse.class)
                    .setParameter("courseId", idCourse)
                    .setParameter("studentId", idStudent)
                    .uniqueResult();

            if (rc != null) {
                session.update(rc);
            } else {
                rc = new RegisterCourse();
                rc.setStudent(studentRepo.findById(idStudent));
                rc.setCourse(courseRepo.findById(idCourse));
                session.save(rc);
            }

            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            System.err.println("Error updating registration: " + e.getMessage());
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean deleteStudent(String idStudent) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            int deleted = session.createQuery(
                            "DELETE FROM RegisterCourse rc WHERE rc.student.id = :studentId")
                    .setParameter("studentId", idStudent)
                    .executeUpdate();

            tx.commit();
            return deleted > 0;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            System.err.println("Error deleting registrations: " + e.getMessage());
            return false;
        } finally {
            if (session != null) session.close();
        }
    }

}
