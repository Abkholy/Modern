package modern.service;

import modern.shared.dto.collageDTO;

import java.util.List;

public interface collageService {
    collageDTO create(collageDTO record);

    collageDTO getbyId(String id);

    collageDTO getbyCode(String code);

    List<collageDTO> getAll(int start, int limit);

    void update(collageDTO record);

    void delete(collageDTO record);
}
