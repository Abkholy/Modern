package modern.service;

import modern.shared.dto.subjectDTO;

import java.util.List;

public interface subjectService {
    subjectDTO create(subjectDTO record);

    subjectDTO getbyId(String id);

    subjectDTO getbyCode(String code);

    List<subjectDTO> getAll(int start, int limit);

    void update(subjectDTO record);

    void delete(subjectDTO record);

    subjectDTO save(subjectDTO record);

}
