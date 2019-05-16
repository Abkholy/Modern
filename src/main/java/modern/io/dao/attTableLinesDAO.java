package modern.io.dao;

import modern.shared.dto.attTableLinesDTO;

import java.util.List;

public interface attTableLinesDAO {
    void openConnection();

    attTableLinesDTO getById(String id);

    attTableLinesDTO saveOne(attTableLinesDTO attTableLines);

    List<attTableLinesDTO> getAll(int start, int limit);

    void updateOne(attTableLinesDTO attTableLines);

    void deleteOne(attTableLinesDTO attTableLines);

    void closeConnection();
}
