package com.data.ss15.service;

import com.data.ss15.model.Resume;

import java.util.List;

public interface ResumeService {
    void addResume(Resume resume);
    List<Resume> getAllResumes();
    void deleteResume(Long id);
    void updateResume(Resume resume);
    Resume findById(Long id);
}
