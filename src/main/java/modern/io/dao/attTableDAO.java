package modern.io.dao;

import modern.shared.dto.attTableDTO;

import java.util.Date;
import java.util.List;

public interface attTableDAO {
    void openConnection();

    attTableDTO getById(String id);

    attTableDTO getByCode(String code);

    attTableDTO getBy4Fields(Date date, String locationId, String subjectId, int period);

    attTableDTO saveOne(attTableDTO attendanceTable);

    List<attTableDTO> getAll(int start, int limit);

    void updateOne(attTableDTO attTableDTO);

    void deleteOne(attTableDTO attTableDTO);

    void closeConnection();
}
