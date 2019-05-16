package modern.service.impl;

import modern.exceptions.CouldNotDeleteRecordException;
import modern.exceptions.CouldNotUpdateRecordException;
import modern.exceptions.MissingRequiredFieldException;
import modern.exceptions.NoRecordFoundException;
import modern.io.dao.locationTypeDAO;
import modern.service.locationTypeService;
import modern.shared.dto.locationTypeDTO;
import modern.ui.response.sys.ErrorMessages;
import modern.utils.EntityUtils;

import java.util.List;

public class locationTypeServiceImpl implements locationTypeService {
    locationTypeDAO database;
    EntityUtils entityUtils = new EntityUtils();

    public locationTypeServiceImpl(locationTypeDAO database) {
        this.database = database;
    }

    @Override
    public locationTypeDTO create(locationTypeDTO record) {
        if (record.getCode() == null) {
            throw new MissingRequiredFieldException(ErrorMessages.MISSING_REQUIRED_FIELD.name() + "  collage Code is required");
        } else {
            locationTypeDTO returnValue = null;
            // Check if user already exists
//            locationTypeDTO existingRecord = this.getbyCode(record.getCode());
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
    public locationTypeDTO getbyId(String id) {
        locationTypeDTO returnValue = null;
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
    public locationTypeDTO getbyCode(String code) {
        locationTypeDTO locationTypeDTO = null;

        if (code == null || code.isEmpty()) {
            return locationTypeDTO;
        }

        try {
            this.database.openConnection();
            locationTypeDTO = this.database.getByCode(code);
        } finally {
            this.database.closeConnection();
        }

        return locationTypeDTO;
    }

    @Override
    public List<locationTypeDTO> getAll(int start, int limit) {
        List<locationTypeDTO> locationTypeDTOS = null;

        // Get users from database
        try {
            this.database.openConnection();
            locationTypeDTOS = this.database.getAll(start, limit);
        } finally {
            this.database.closeConnection();
        }

        return locationTypeDTOS;
    }

    @Override
    public void update(locationTypeDTO record) {
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
    public void delete(locationTypeDTO record) {
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
    public locationTypeDTO save(locationTypeDTO record) {
        locationTypeDTO returnValue = null;
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
