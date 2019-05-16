package modern.service.impl;

import modern.exceptions.CouldNotDeleteRecordException;
import modern.exceptions.CouldNotUpdateRecordException;
import modern.exceptions.MissingRequiredFieldException;
import modern.exceptions.NoRecordFoundException;
import modern.io.dao.subjectDAO;
import modern.service.subjectService;
import modern.shared.dto.subjectDTO;
import modern.ui.response.sys.ErrorMessages;
import modern.utils.EntityUtils;

import java.util.List;

public class subjectServiceImpl implements subjectService {
    subjectDAO database;
    EntityUtils entityUtils = new EntityUtils();

    public subjectServiceImpl(subjectDAO database) {
        this.database = database;
    }

    @Override
    public subjectDTO create(subjectDTO record) {
        if (record.getCode() == null) {
            throw new MissingRequiredFieldException(ErrorMessages.MISSING_REQUIRED_FIELD.name() + "  collage Code is required");
        } else {
            subjectDTO returnValue = null;
            // Check if user already exists
//            subjectDTO existingRecord = this.getbyCode(record.getCode());
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
    public subjectDTO getbyId(String id) {
        subjectDTO returnValue = null;
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
    public subjectDTO getbyCode(String code) {
        subjectDTO subjectDTO = null;

        if (code == null || code.isEmpty()) {
            return subjectDTO;
        }

        try {
            this.database.openConnection();
            subjectDTO = this.database.getByCode(code);
        } finally {
            this.database.closeConnection();
        }

        return subjectDTO;
    }

    @Override
    public List<subjectDTO> getAll(int start, int limit) {
        List<subjectDTO> subjectDTOS = null;

        // Get users from database
        try {
            this.database.openConnection();
            subjectDTOS = this.database.getAll(start, limit);
        } finally {
            this.database.closeConnection();
        }

        return subjectDTOS;
    }

    @Override
    public void update(subjectDTO record) {
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
    public void delete(subjectDTO record) {
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
    public subjectDTO save(subjectDTO record) {
        subjectDTO returnValue = null;
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
