package modern.io.dao;

import modern.shared.dto.timeTableDTO;

import java.util.List;

public interface timeTableDAO {
    void openConnection();

    timeTableDTO getById(String id);

    timeTableDTO getByCode(String code);

    timeTableDTO saveOne(timeTableDTO record);

    List<timeTableDTO> getAll(int start, int limit);

    void updateOne(timeTableDTO timeTableDTO);

    void deleteOne(timeTableDTO timeTableDTO);

    void closeConnection();
}
