package com.data.ss15.respository;

import com.data.ss15.model.Resume;

import java.util.List;

public interface ResumeRepository {
    void addResume(Resume resume);
    List<Resume> getAllResumes();
    void deleteResume(Long id);
    Resume findById(Long id);
    void updateResume(Resume resume);
}
