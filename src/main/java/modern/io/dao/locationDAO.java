package modern.io.dao;

import modern.shared.dto.locationDTO;

import java.util.List;

public interface locationDAO {
    void openConnection();

    locationDTO getById(String id);

    locationDTO getByCode(String code);

    locationDTO getByMacAddress(String macAddress);

    locationDTO saveOne(locationDTO location);

    List<locationDTO> getAll(int start, int limit);

    void updateOne(locationDTO locationDTO);

    void deleteOne(locationDTO locationDTO);

    void closeConnection();
}
