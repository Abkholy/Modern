package modern.io.dao;

import modern.shared.dto.collageDTO;

import java.util.List;

public interface collageDAO {
    void openConnection();

    collageDTO getById(String id);

    collageDTO getByCode(String code);

    collageDTO saveOne(collageDTO collage);

    List<collageDTO> getAll(int start, int limit);

    void updateOne(collageDTO collageDTO);

    void deleteOne(collageDTO collageDTO);

    void closeConnection();
}
