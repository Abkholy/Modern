package modern.service.impl;

import modern.exceptions.CouldNotDeleteRecordException;
import modern.exceptions.CouldNotUpdateRecordException;
import modern.exceptions.MissingRequiredFieldException;
import modern.exceptions.NoRecordFoundException;
import modern.io.dao.semesterDAO;
import modern.service.semesterService;
import modern.shared.dto.semesterDTO;
import modern.ui.response.sys.ErrorMessages;
import modern.utils.EntityUtils;

import java.util.List;

public class semesterServiceImpl implements semesterService {
    semesterDAO database;
    EntityUtils entityUtils = new EntityUtils();

    public semesterServiceImpl(semesterDAO database) {
        this.database = database;
    }

    @Override
    public semesterDTO create(semesterDTO record) {
        if (record.getCode() == null) {
            throw new MissingRequiredFieldException(ErrorMessages.MISSING_REQUIRED_FIELD.name() + "  collage Code is required");
        } else {
            semesterDTO returnValue = null;
            // Check if user already exists
//            semesterDTO existingRecord = this.getbyCode(record.getCode());
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
    public semesterDTO getbyId(String id) {
        semesterDTO returnValue = null;
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
    public semesterDTO getbyCode(String code) {
        semesterDTO semesterDTO = null;

        if (code == null || code.isEmpty()) {
            return semesterDTO;
        }

        try {
            this.database.openConnection();
            semesterDTO = this.database.getByCode(code);
        } finally {
            this.database.closeConnection();
        }

        return semesterDTO;
    }

    @Override
    public List<semesterDTO> getAll(int start, int limit) {
        List<semesterDTO> semesterDTOS = null;

        // Get users from database
        try {
            this.database.openConnection();
            semesterDTOS = this.database.getAll(start, limit);
        } finally {
            this.database.closeConnection();
        }

        return semesterDTOS;
    }

    @Override
    public void update(semesterDTO record) {
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
    public void delete(semesterDTO record) {
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

    public semesterDTO save(semesterDTO record) {
        semesterDTO returnValue = null;
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
