package modern.service.impl;

import modern.exceptions.CouldNotDeleteRecordException;
import modern.exceptions.CouldNotUpdateRecordException;
import modern.exceptions.MissingRequiredFieldException;
import modern.exceptions.NoRecordFoundException;
import modern.io.dao.usersDAO;
import modern.service.usersService;
import modern.shared.dto.usersDTO;
import modern.ui.response.sys.ErrorMessages;
import modern.utils.EntityUtils;

import java.util.List;

public class usersServiceImpl implements usersService {
    usersDAO database;
    EntityUtils EntityUtils = new EntityUtils();

    public usersServiceImpl(usersDAO database) {
        this.database = database;
    }

    @Override
    public usersDTO create(usersDTO record) {
        usersDTO returnValue = null;


        // Check if user already exists
//        usersDTO existingUser = this.getByEmail(record.getEmail());
//        if (existingUser != null) {
//            throw new CouldNotCreateRecordException(ErrorMessages.RECORD_ALREADY_EXISTS.name());
//        }

        // Generate secure public user id
        String userId = EntityUtils.generateEntityId(30);
        record.setId(userId);
        record.setUserId(userId);

        // Generate salt
        String salt = EntityUtils.getSalt(30);
        // Generate secure password
        String encryptedPassword = EntityUtils.generateSecurePassword(record.getPassword(), salt);
        record.setSalt(salt);
        record.setEncryptedPassword(encryptedPassword);

        // Record data into a database
        returnValue = this.save(record);


        // Return back the user profile
        return returnValue;
    }

    @Override
    public usersDTO getbyId(String id) {
        usersDTO returnValue = null;
        try {
            this.database.openConnection();
            returnValue = this.database.getById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new NoRecordFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        } finally {
            this.database.closeConnection();
        }
        return returnValue;
    }

    @Override
    public usersDTO getByEmail(String email) {
        usersDTO usersDTO = null;

        if (email == null || email.isEmpty()) {
            return usersDTO;
        }

        try {
            this.database.openConnection();
            usersDTO = this.database.getbyEmail(email);
        } finally {
            this.database.closeConnection();
        }

        return usersDTO;
    }

    @Override
    public List<usersDTO> getAll(int start, int limit) {
        List<usersDTO> usersDTOS = null;

        // Get users from database
        try {
            this.database.openConnection();
            usersDTOS = this.database.getAll(start, limit);
        } finally {
            this.database.closeConnection();
        }

        return usersDTOS;
    }

    @Override
    public void update(usersDTO record) {
        if (record.getId() == null) {
            throw new MissingRequiredFieldException(ErrorMessages.MISSING_REQUIRED_FIELD.name() + "location ID is required");
        } else {
            try {
                // Connect to database
                this.database.openConnection();
                // Update User Details
                this.database.updateOne(record);

            } catch (Exception ex) {
                throw new CouldNotUpdateRecordException(ex.getMessage());
            } finally {
                this.database.closeConnection();
            }
        }
    }

    @Override
    public void delete(usersDTO record) {
        try {
            this.database.openConnection();
            this.database.deleteOne(record);
        } catch (Exception ex) {
            throw new CouldNotDeleteRecordException(ex.getMessage());
        } finally {
            this.database.closeConnection();
        }

        // Verify that user is deleted
        try {
            record = getbyId(record.getId());
        } catch (NoRecordFoundException ex) {
            record = null;
        }

        if (record != null) {
            throw new CouldNotDeleteRecordException(
                    ErrorMessages.COULD_NOT_DELETE_RECORD.getErrorMessage());
        }
    }

    @Override
    public usersDTO save(usersDTO record) {
        usersDTO returnValue = null;
        // Connect to database
        try {
            this.database.openConnection();
            returnValue = this.database.saveOne(record);
        } finally {
            this.database.closeConnection();
        }

        return returnValue;
    }
}
