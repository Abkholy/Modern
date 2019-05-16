package modern.service.impl;

import modern.exceptions.CouldNotDeleteRecordException;
import modern.exceptions.CouldNotUpdateRecordException;
import modern.exceptions.MissingRequiredFieldException;
import modern.exceptions.NoRecordFoundException;
import modern.io.dao.registrationLinesDAO;
import modern.service.registrationLinesService;
import modern.shared.dto.registrationLinesDTO;
import modern.ui.response.sys.ErrorMessages;
import modern.utils.EntityUtils;

import java.util.List;

public class registrationLinesServiceImpl implements registrationLinesService {
    registrationLinesDAO database;
    EntityUtils entityUtils = new EntityUtils();

    public registrationLinesServiceImpl(registrationLinesDAO database) {
        this.database = database;
    }

    @Override
    public registrationLinesDTO create(registrationLinesDTO record) {
        if (record.getRegistration() == null) {
            throw new MissingRequiredFieldException(ErrorMessages.MISSING_REQUIRED_FIELD.name() + "  collage Code is required");
        } else {
            registrationLinesDTO returnValue = null;
            // Check if user already exists
//            registrationLinesDTO existingRecord = this.getbyId(record.getId());
//            if (existingRecord != null) {
//                throw new CouldNotCreateRecordException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
//            }

            String Id = entityUtils.generateEntityId(30);
            record.setId(Id);
            // Record data into a database
            returnValue = this.save(record);
            // Return back the user profile
            return returnValue;
        }
    }

    @Override
    public registrationLinesDTO getbyId(String id) {
        registrationLinesDTO returnValue = null;
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
    public List<registrationLinesDTO> getAll(int start, int limit) {
        List<registrationLinesDTO> registrationLinesDTOS = null;

        // Get users from database
        try {
            this.database.openConnection();
            registrationLinesDTOS = this.database.getAll(start, limit);
        } finally {
            this.database.closeConnection();
        }

        return registrationLinesDTOS;
    }

    @Override
    public void update(registrationLinesDTO record) {
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
    public void delete(registrationLinesDTO record) {
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
    public registrationLinesDTO save(registrationLinesDTO record) {
        registrationLinesDTO returnValue = null;
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
