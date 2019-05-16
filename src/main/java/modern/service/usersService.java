package modern.service;

import modern.shared.dto.usersDTO;

import java.util.List;

public interface usersService {

    usersDTO create(usersDTO record);

    usersDTO getbyId(String id);

    usersDTO getByEmail(String email);

    List<usersDTO> getAll(int start, int limit);

    void update(usersDTO record);

    void delete(usersDTO record);

    usersDTO save(usersDTO record);

}
