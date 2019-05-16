package modern.io.dao;

import modern.shared.dto.locationTypeDTO;

import java.util.List;

public interface locationTypeDAO {
    void openConnection();

    locationTypeDTO getById(String id);

    locationTypeDTO getByCode(String code);

    locationTypeDTO saveOne(locationTypeDTO locationType);

    List<locationTypeDTO> getAll(int start, int limit);

    void updateOne(locationTypeDTO locationTypeDTO);

    void deleteOne(locationTypeDTO locationTypeDTO);

    void closeConnection();
}
