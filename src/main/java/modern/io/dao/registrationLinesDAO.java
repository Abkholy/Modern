package modern.io.dao;

import modern.shared.dto.registrationLinesDTO;

import java.util.List;

public interface registrationLinesDAO {
    void openConnection();

    registrationLinesDTO getById(String id);

    registrationLinesDTO saveOne(registrationLinesDTO record);

    List<registrationLinesDTO> getAll(int start, int limit);

    void updateOne(registrationLinesDTO registrationLinesDTO);

    void deleteOne(registrationLinesDTO registrationLinesDTO);

    void closeConnection();
}
