package modern.io.dao;

import modern.shared.dto.semesterDTO;

import java.util.List;

public interface semesterDAO {
    void openConnection();

    semesterDTO getById(String id);

    semesterDTO getByCode(String code);

    semesterDTO saveOne(semesterDTO semester);

    List<semesterDTO> getAll(int start, int limit);

    void updateOne(semesterDTO semesterDTO);

    void deleteOne(semesterDTO locationDTO);

    void closeConnection();
}
