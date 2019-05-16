package modern.service;

import modern.shared.dto.attTableDTO;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface attTableService {
    attTableDTO create(attTableDTO bdto) throws ParseException;

    attTableDTO getbyId(String id);

    attTableDTO getbyCode(String code);

    attTableDTO getBy4Fields(Date date, String locationId, String subjectId, int period);

    List<attTableDTO> getAll(int start, int limit);

    void update(attTableDTO bdto);

    void delete(attTableDTO bdto);

    attTableDTO save(attTableDTO record);

}
