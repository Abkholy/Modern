package modern.io.dao;

import modern.shared.dto.studentDTO;

import java.util.List;

public interface studentDAO {
    void openConnection();

    studentDTO getById(String id);

    studentDTO getByCode(String code);

    studentDTO getByCodeAndMac(String code, String mac);

    studentDTO saveOne(studentDTO record);

    List<studentDTO> getAll(int start, int limit);

    void updateOne(studentDTO studentDTO);

    void deleteOne(studentDTO studentDTO);

    void closeConnection();
}
