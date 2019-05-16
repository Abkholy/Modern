package modern.service;


import modern.shared.dto.locationTypeDTO;

import java.util.List;

public interface locationTypeService {
    locationTypeDTO create(locationTypeDTO record);

    locationTypeDTO getbyId(String id);

    locationTypeDTO getbyCode(String code);

    List<locationTypeDTO> getAll(int start, int limit);

    void update(locationTypeDTO record);

    void delete(locationTypeDTO record);

    locationTypeDTO save(locationTypeDTO record);
}
