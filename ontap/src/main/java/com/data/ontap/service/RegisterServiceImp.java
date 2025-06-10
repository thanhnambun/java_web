package com.data.ontap.service;

import com.data.ontap.model.Course;
import com.data.ontap.model.RegisterCourse;
import com.data.ontap.model.Student;
import com.data.ontap.repository.RegisterCourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterServiceImp implements RegisterService {
    @Autowired
    private RegisterCourseRepo registerCourseRepo;
    @Override
    public List<RegisterCourse> findAll(int page, int pageSize) {
        return registerCourseRepo.findAll(page, pageSize);
    }

    @Override
    public List<Course> findByIdCourse(Long idCourse) {
        return registerCourseRepo.findByIdCourse(idCourse);
    }

    @Override
    public List<Student> findByIdStudent(String idStudent) {
        return registerCourseRepo.findByIdStudent(idStudent);
    }

    @Override
    public boolean addCourse(Long idCourse, String idStudent) {
        return registerCourseRepo.addCourse(idCourse, idStudent);
    }

    @Override
    public boolean updateCourse(Long idCourse, String idStudent) {
        return registerCourseRepo.updateCourse(idCourse, idStudent);
    }

    @Override
    public boolean deleteStudent(String idStudent) {
        return registerCourseRepo.deleteStudent(idStudent);
    }
}
