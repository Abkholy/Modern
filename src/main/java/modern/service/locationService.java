package modern.service;

import modern.shared.dto.locationDTO;

import java.util.List;

public interface locationService {
    locationDTO create(locationDTO record);

    locationDTO getbyId(String id);

    locationDTO getbyCode(String code);

    locationDTO getbyMacAddress(String macAddress);

    List<locationDTO> getAll(int start, int limit);

    void update(locationDTO record);

    void delete(locationDTO record);
}
