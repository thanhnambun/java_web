package com.data.ontap.service;

import com.data.ontap.model.Course;
import com.data.ontap.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServiceImp implements CourseService{
    @Autowired
    private CourseRepo courseRepo;
    @Override
    public List<Course> getAll(int page, int pageSize) {
        return courseRepo.findAll(page, pageSize);
    }

    @Override
    public List<Course> searchByName(String name, int page, int pageSize) {
        return courseRepo.findByName(name,page,pageSize);
    }

    @Override
    public Course getById(Long id) {
        return courseRepo.findById(id);
    }

    @Override
    public boolean createCourse(Course course) {
        return courseRepo.save(course);
    }

    @Override
    public boolean updateCourse(Course course) {
        return courseRepo.update(course);
    }

    @Override
    public boolean deleteCourse(Long id) {
        return courseRepo.delete(id);
    }

    @Override
    public List<Course> findAllCous() {
        return courseRepo.findAllCous();
    }
}
