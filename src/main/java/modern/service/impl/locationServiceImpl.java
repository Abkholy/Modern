package modern.service.impl;

import modern.exceptions.CouldNotDeleteRecordException;
import modern.exceptions.CouldNotUpdateRecordException;
import modern.exceptions.MissingRequiredFieldException;
import modern.exceptions.NoRecordFoundException;
import modern.io.dao.locationDAO;
import modern.service.locationService;
import modern.shared.dto.locationDTO;
import modern.ui.response.sys.ErrorMessages;
import modern.utils.EntityUtils;

import java.util.List;

public class locationServiceImpl implements locationService {
    locationDAO database;
    EntityUtils entityUtils = new EntityUtils();

    public locationServiceImpl(locationDAO database) {
        this.database = database;
    }

    @Override
    public locationDTO create(locationDTO record) {
        if (record.getCode() == null) {
            throw new MissingRequiredFieldException(ErrorMessages.MISSING_REQUIRED_FIELD.name() + "  collage Code is required");
        } else {
            locationDTO returnValue = null;
            // Check if user already exists
//            locationDTO existingRecord = this.getbyCode(record.getCode());
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
    public locationDTO getbyId(String id) {
        locationDTO returnValue = null;
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
    public locationDTO getbyCode(String code) {
        locationDTO locationDTO = null;

        if (code == null || code.isEmpty()) {
            return locationDTO;
        }

        try {
            this.database.openConnection();
            locationDTO = this.database.getByCode(code);
        } finally {
            this.database.closeConnection();
        }

        return locationDTO;
    }

    @Override
    public locationDTO getbyMacAddress(String macAddress) {
        locationDTO locationDTO = null;

        if (macAddress == null || macAddress.isEmpty()) {
            return locationDTO;
        }

        try {
            this.database.openConnection();
            locationDTO = this.database.getByMacAddress(macAddress);
        } finally {
            this.database.closeConnection();
        }

        return locationDTO;
    }

    @Override
    public List<locationDTO> getAll(int start, int limit) {
        List<locationDTO> locationDTOS = null;

        // Get users from database
        try {
            this.database.openConnection();
            locationDTOS = this.database.getAll(start, limit);
        } finally {
            this.database.closeConnection();
        }

        return locationDTOS;
    }

    @Override
    public void update(locationDTO record) {
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
    public void delete(locationDTO record) {
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

    private locationDTO save(locationDTO eDTO) {
        locationDTO returnValue = null;
        // Connect to database
        try {
            this.database.openConnection();
            returnValue = this.database.saveOne(eDTO);
        } finally {
            this.database.closeConnection();
        }

        return returnValue;
    }
}
