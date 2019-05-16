package modern.service;

import modern.shared.dto.timeTableDTO;

import java.text.ParseException;
import java.util.List;

public interface timeTableService {
    timeTableDTO create(timeTableDTO record) throws ParseException;

    timeTableDTO getbyId(String id);

    timeTableDTO getbyCode(String code);

    List<timeTableDTO> getAll(int start, int limit);

    void update(timeTableDTO record);

    void delete(timeTableDTO record);

    timeTableDTO save(timeTableDTO record);

}
