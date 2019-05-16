package modern.service;

import modern.shared.dto.attTableLinesDTO;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;

public interface attTableLinesService {
    attTableLinesDTO create(attTableLinesDTO record) throws ParseException;

    attTableLinesDTO createFull(attTableLinesDTO record) throws ParseException;

    attTableLinesDTO getbyId(String id);

    List<attTableLinesDTO> getRecord(Date date, DayOfWeek dayOfWeek);

    List<attTableLinesDTO> getAll(int start, int limit);

    void update(attTableLinesDTO record);

    void delete(attTableLinesDTO record);

    attTableLinesDTO save(attTableLinesDTO record);

}
