package modern.service;


import modern.shared.dto.studentDTO;

import java.util.List;

public interface studentService {
    studentDTO create(studentDTO record);

    studentDTO getbyId(String id);

    studentDTO getbyCode(String code);

    studentDTO getbyCodeAndMacAddress(String code, String mac);

    List<studentDTO> getAll(int start, int limit);

    void update(studentDTO record);

    void delete(studentDTO record);

    studentDTO save(studentDTO record);

}
