package ra.edu.project.repository.recruitmentPosition;

import ra.edu.project.entity.recruitmentPosition.RecruitmentPosition;
import ra.edu.project.entity.recruitmentPosition.Status;

import java.util.List;

public interface RecruitmentPositionRepository {

    List<RecruitmentPosition> findAllActive(int page, int size);

    List<RecruitmentPosition> findAll(int page, int size);

    long countActive();

    long countAll();

    RecruitmentPosition findById(int id);

    void save(RecruitmentPosition position);

    void updateStatus(int id, Status status);

    boolean existsByName(String name);

    List<RecruitmentPosition> searchByName(String keyword, int page, int size);

    long countByName(String keyword);
}
