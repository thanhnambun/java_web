package ra.edu.project.repository.technology;

import ra.edu.project.entity.technology.Technology;

import java.util.List;

public interface TechnologyRepository {
    List<Technology> findTechnology();
    boolean saveTechnology(Technology technology);
    boolean deleteTechnology(Technology technology);
    boolean updateTechnology(Technology technology);
    List<Technology> findAll(int page, int pageSize);
    Technology findTechnologyById(int id);
    List<Technology> findTechnologyByName(String name, int page, int pageSize);
    Technology findByExactName(String name);
    int checkTechnologyByCandidate(Technology technology);
    int checkTechnologyByRecruitmentPosition(Technology technology);
    List<Technology> findByNameIn(List<String> names);
    List<Technology> findAllByIds(List<Integer> ids);

}
