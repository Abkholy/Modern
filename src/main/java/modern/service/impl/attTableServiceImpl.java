package modern.service.impl;

import modern.exceptions.CouldNotDeleteRecordException;
import modern.exceptions.CouldNotUpdateRecordException;
import modern.exceptions.MissingRequiredFieldException;
import modern.exceptions.NoRecordFoundException;
import modern.io.dao.attTableDAO;
import modern.service.attTableService;
import modern.shared.dto.attTableDTO;
import modern.ui.response.sys.ErrorMessages;
import modern.utils.EntityUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class attTableServiceImpl implements attTableService {
    attTableDAO database;
    EntityUtils entityUtils = new EntityUtils();

    public attTableServiceImpl(attTableDAO database) {
        this.database = database;
    }

    @Override
    public attTableDTO create(attTableDTO record) throws ParseException {
        if (record.getCode() == null) {
            throw new MissingRequiredFieldException(ErrorMessages.MISSING_REQUIRED_FIELD.name() + "  collage Code is required");
        } else {
            attTableDTO returnValue = null;
            // Check if user already exists
//            attTableDTO existingRecord = this.getbyCode(record.getCode());
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
    public attTableDTO getbyId(String id) {
        attTableDTO returnValue = null;
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
    public attTableDTO getbyCode(String code) {
        attTableDTO attTableDTO = null;

        if (code == null || code.isEmpty()) {
            return attTableDTO;
        }

        try {
            this.database.openConnection();
            attTableDTO = this.database.getByCode(code);
        } finally {
            this.database.closeConnection();
        }

        return attTableDTO;
    }

    @Override
    public attTableDTO getBy4Fields(Date date, String locationId, String subjectId, int period) {
        attTableDTO attTableDTO = null;

        if (date == null) {
            return attTableDTO;
        }

        try {
            this.database.openConnection();
            attTableDTO = this.database.getBy4Fields(date, locationId, subjectId, period);
        } finally {
            this.database.closeConnection();
        }

        return attTableDTO;
    }

    @Override
    public List<attTableDTO> getAll(int start, int limit) {
        List<attTableDTO> attTableDTOS = null;

        // Get users from database
        try {
            this.database.openConnection();
            attTableDTOS = this.database.getAll(start, limit);
        } finally {
            this.database.closeConnection();
        }

        return attTableDTOS;
    }

    @Override
    public void update(attTableDTO record) {
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
    public void delete(attTableDTO record) {
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
    public attTableDTO save(attTableDTO record) {
        attTableDTO returnValue = null;
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
