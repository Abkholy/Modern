package modern.io.dao;

import modern.shared.dto.subjectDTO;

import java.util.List;

public interface subjectDAO {
    void openConnection();

    subjectDTO getById(String id);

    subjectDTO getByCode(String code);

    subjectDTO saveOne(subjectDTO record);

    List<subjectDTO> getAll(int start, int limit);

    void updateOne(subjectDTO subjectDTO);

    void deleteOne(subjectDTO subjectDTO);

    void closeConnection();
}
