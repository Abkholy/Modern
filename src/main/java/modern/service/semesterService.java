package modern.service;

import modern.shared.dto.semesterDTO;

import java.util.List;

public interface semesterService {
    semesterDTO create(semesterDTO record);

    semesterDTO getbyId(String id);

    semesterDTO getbyCode(String code);

    List<semesterDTO> getAll(int start, int limit);

    void update(semesterDTO record);

    void delete(semesterDTO record);

}
