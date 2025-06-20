package ra.edu.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.edu.project.dto.RecruitmentPositionDTO;
import ra.edu.project.entity.recruitmentPosition.RecruitmentPosition;
import ra.edu.project.entity.recruitmentPosition.Status;
import ra.edu.project.entity.technology.Technology;
import ra.edu.project.repository.recruitmentPosition.RecruitmentPositionRepository;
import ra.edu.project.repository.technology.TechnologyRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecruitmentPositionService {

    @Autowired
    private RecruitmentPositionRepository recruitmentPositionRepository;

    @Autowired
    private TechnologyService technologyService;

    @Transactional(readOnly = true)
    public List<RecruitmentPositionDTO> findAllActive(int page, int size) {
        List<RecruitmentPosition> positions = recruitmentPositionRepository.findAllActive(page, size);
        return positions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RecruitmentPositionDTO> findAll(int page, int size) {
        List<RecruitmentPosition> positions = recruitmentPositionRepository.findAll(page, size);
        return positions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public long getTotalActiveItems() {
        return recruitmentPositionRepository.countActive();
    }

    @Transactional(readOnly = true)
    public long getTotalItems() {
        return recruitmentPositionRepository.countAll();
    }

    @Transactional(readOnly = true)
    public RecruitmentPositionDTO findById(int id) {
        RecruitmentPosition position = recruitmentPositionRepository.findById(id);
        if (position == null || position.getStatus() == Status.INACTIVE) {
            throw new RuntimeException("Recruitment position not found");
        }
        return convertToDTO(position);
    }
    @Transactional(readOnly = true)
    public RecruitmentPosition findByIdRecruitmentPosition(int id) {
        return recruitmentPositionRepository.findById(id);
    }

    @Transactional
    public void save(RecruitmentPositionDTO dto) {
        // Kiểm tra tên đã tồn tại chưa (đối với thêm mới hoặc cập nhật khác tên)
        if (dto.getId() == 0) { // Thêm mới
            if (recruitmentPositionRepository.existsByName(dto.getName())) {
                throw new RuntimeException("Tên vị trí tuyển dụng đã tồn tại");
            }
        } else { // Cập nhật
            RecruitmentPosition existing = recruitmentPositionRepository.findById(dto.getId());
            if (existing == null) {
                throw new RuntimeException("Vị trí không tồn tại");
            }
            if (!existing.getName().equals(dto.getName()) &&
                    recruitmentPositionRepository.existsByName(dto.getName())) {
                throw new RuntimeException("Tên vị trí tuyển dụng đã tồn tại");
            }
        }

        RecruitmentPosition entity = convertToEntity(dto);

        if (dto.getId() != 0) { // Cập nhật - giữ nguyên ngày tạo và trạng thái
            RecruitmentPosition existing = recruitmentPositionRepository.findById(dto.getId());
            if (existing != null) {
                entity.setCreatedDate(existing.getCreatedDate());
                entity.setStatus(existing.getStatus());
            }
        } else {
            entity.setStatus(Status.ACTIVE);
            entity.setCreatedDate(LocalDate.now());
        }

        List<String> techNames = dto.getTechnologies();
        List<Technology> techList = new ArrayList<>();
        for (String techName : techNames) {
            Integer techId = technologyService.findByExactName(techName);
            Technology technology = new Technology();
            technology.setId(techId);
            techList.add(technology);
        }
        entity.setTechnologies(techList);

        recruitmentPositionRepository.save(entity);
    }


    @Transactional(readOnly = true)
    public List<RecruitmentPositionDTO> searchByName(String keyword, int page, int size) {
        List<RecruitmentPosition> positions = recruitmentPositionRepository.searchByName(keyword, page, size);
        return positions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public long countByName(String keyword) {
        return recruitmentPositionRepository.countByName(keyword);
    }

    @Transactional
    public void delete(int id) {
        recruitmentPositionRepository.updateStatus(id, Status.INACTIVE);
    }
    @Transactional
    public RecruitmentPositionDTO convertToDTO(RecruitmentPosition entity) {
        RecruitmentPositionDTO dto = new RecruitmentPositionDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setMinSalary(entity.getMinSalary());
        dto.setMaxSalary(entity.getMaxSalary());
        dto.setMinExperience(entity.getMinExperience());
        dto.setExpiredDate(entity.getExpiredDate());
        dto.setCreatedDate(entity.getCreatedDate());

        if (entity.getTechnologies() != null) {
            List<String> techNames = entity.getTechnologies()
                    .stream()
                    .map(Technology::getName)
                    .collect(Collectors.toList());
            dto.setTechnologies(techNames);
        } else {
            dto.setTechnologies(new ArrayList<>());
        }
        return dto;
    }
    @Transactional
    public RecruitmentPosition convertToEntity(RecruitmentPositionDTO dto) {
        RecruitmentPosition entity = new RecruitmentPosition();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setMinSalary(dto.getMinSalary());
        entity.setMaxSalary(dto.getMaxSalary());
        entity.setMinExperience(dto.getMinExperience());
        entity.setExpiredDate(dto.getExpiredDate());
        entity.setCreatedDate(dto.getCreatedDate());

        List<Technology> techList = new ArrayList<>();
        for (String techName : dto.getTechnologies()) {
            Integer techId = technologyService.findByExactName(techName);
            if (techId != null) {
                Technology technology = new Technology();
                technology.setId(techId);
                techList.add(technology);
            }
        }
        entity.setTechnologies(techList);

        return entity;
    }
}
