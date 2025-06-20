package ra.edu.project.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.edu.project.dto.TechnologyDTO;
import ra.edu.project.entity.technology.Status;
import ra.edu.project.entity.technology.Technology;
import ra.edu.project.repository.technology.TechnologyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<TechnologyDTO> getAllTechnologies(int page, int pageSize) {
        List<Technology> technologies = technologyRepository.findAll(page, pageSize);
        return technologies.stream()
                .map(tech -> modelMapper.map(tech, TechnologyDTO.class))
                .collect(Collectors.toList());
    }
    @Transactional
    public int findByExactName(String name){
        return technologyRepository.findByExactName(name).getId();
    }


    @Transactional
    public boolean addTechnology(Technology technology) {
        Technology existing = technologyRepository.findByExactName(technology.getName());
        if (existing != null) {
            if ("INACTIVE".equals(existing.getStatus().name())) {
                existing.setStatus(Status.ACTIVE);
                technologyRepository.updateTechnology(existing);
                return true;
            }
            return false;
        }
        return technologyRepository.saveTechnology(technology);
    }

    @Transactional
    public boolean updateTechnology(Technology technology) {
        Technology existing = technologyRepository.findTechnologyById(technology.getId());
        if (existing == null) {
            return false;
        }
        Technology checkName = technologyRepository.findByExactName(technology.getName());
        if (checkName != null && checkName.getId() != technology.getId()) {
            return false;
        }
        return technologyRepository.updateTechnology(technology);
    }
        @Transactional
    public List<String> getAllTechnologyNames() {
        return technologyRepository.findTechnology()
                .stream()
                .map(Technology::getName)
                .distinct()
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean deleteTechnology(int id) {
        Technology existing = technologyRepository.findTechnologyById(id);
        if (existing == null) {
            return false;
        }
        if (hasForeignKeyConstraint(existing)) {
            existing.setStatus(Status.INACTIVE);
            return technologyRepository.updateTechnology(existing);
        } else {
            return technologyRepository.deleteTechnology(existing);
        }
    }

    private boolean hasForeignKeyConstraint(Technology technology) {
        int checkCandidate = technologyRepository.checkTechnologyByCandidate(technology);
        int checkRecruitmentPosition = technologyRepository.checkTechnologyByRecruitmentPosition(technology);
        return checkCandidate > 0 || checkRecruitmentPosition > 0;
    }

    @Transactional(readOnly = true)
    public List<TechnologyDTO> searchByName(String name, int page, int pageSize) {
        List<Technology> technologies = technologyRepository.findTechnologyByName(name, page, pageSize);
        return technologies.stream()
                .map(tech -> modelMapper.map(tech, TechnologyDTO.class))
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public TechnologyDTO getTechnologyById(int id) {
        Technology technology = technologyRepository.findTechnologyById(id);
        return technology != null ? modelMapper.map(technology, TechnologyDTO.class) : null;
    }
    @Transactional(readOnly = true)
    public Technology getTechnologyEntityById(int id) {
        return technologyRepository.findTechnologyById(id);
    }

}
