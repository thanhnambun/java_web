package com.data.ss15.service;

import com.data.ss15.respository.ResumeRepositoryImp;
import com.data.ss15.respository.ResumeRepository;
import com.data.ss15.model.Resume;
import com.data.ss15.service.ResumeService;

import java.util.List;

public class ResumeServiceImpl implements ResumeService {
    private ResumeRepository resumeDAO = new ResumeRepositoryImp();

    @Override
    public void addResume(Resume resume) {
        resumeDAO.addResume(resume);
    }

    @Override
    public List<Resume> getAllResumes() {
        return resumeDAO.getAllResumes();
    }

    @Override
    public void deleteResume(Long id) {
        resumeDAO.deleteResume(id);
    }

    @Override
    public void updateResume(Resume resume) {
        resumeDAO.updateResume(resume);
    }

    @Override
    public Resume findById(Long id) {
        return resumeDAO.findById(id);
    }
}