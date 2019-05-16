package modern.io.dao;

import modern.shared.dto.registrationDTO;

import java.util.Date;
import java.util.List;

public interface registrationDAO {
    void openConnection();

    registrationDTO getById(String id);

    registrationDTO getByCode(String code);

    registrationDTO getByStudentId(String studentId, Date date);

    registrationDTO saveOne(registrationDTO record);

    List<registrationDTO> getAll(int start, int limit);

    void updateOne(registrationDTO registrationDTO);

    void deleteOne(registrationDTO registrationDTO);

    void closeConnection();
}
