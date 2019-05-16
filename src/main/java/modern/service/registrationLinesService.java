package modern.service;

import modern.shared.dto.registrationLinesDTO;

import java.util.List;

public interface registrationLinesService {
    registrationLinesDTO create(registrationLinesDTO record);

    registrationLinesDTO getbyId(String id);


    List<registrationLinesDTO> getAll(int start, int limit);

    void update(registrationLinesDTO record);

    void delete(registrationLinesDTO record);

    registrationLinesDTO save(registrationLinesDTO record);

}
