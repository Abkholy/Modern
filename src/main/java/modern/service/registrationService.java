package modern.service;

import modern.shared.dto.registrationDTO;

import java.util.Date;
import java.util.List;

public interface registrationService {
    registrationDTO create(registrationDTO record);

    registrationDTO getbyId(String id);

    registrationDTO getbyCode(String code);

    registrationDTO getbyStudentId(String studentId, Date date);

    List<registrationDTO> getAll(int start, int limit);

    void update(registrationDTO record);

    void delete(registrationDTO record);

    registrationDTO save(registrationDTO record);

}
