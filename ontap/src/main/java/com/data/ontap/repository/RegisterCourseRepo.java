package com.data.ontap.repository;

import com.data.ontap.model.Course;
import com.data.ontap.model.RegisterCourse;
import com.data.ontap.model.Student;

import java.util.List;

public interface RegisterCourseRepo {
    List<RegisterCourse> findAll(int page, int pageSize);
    List<Course> findByIdCourse(Long idCourse);
    List<Student> findByIdStudent(String idStudent);
    boolean addCourse(Long idCourse,String idStudent);
    boolean updateCourse(Long idCourse,String idStudent);
    boolean deleteStudent(String idStudent);
}
