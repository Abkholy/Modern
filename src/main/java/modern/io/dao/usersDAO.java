package modern.io.dao;

import modern.shared.dto.usersDTO;

import java.util.List;

public interface usersDAO {
    void openConnection();

    usersDTO getById(String id);

    usersDTO getbyEmail(String email);

    usersDTO saveOne(usersDTO record);

    List<usersDTO> getAll(int start, int limit);

    void updateOne(usersDTO usersDTO);

    void deleteOne(usersDTO usersDTO);

    void closeConnection();
}
