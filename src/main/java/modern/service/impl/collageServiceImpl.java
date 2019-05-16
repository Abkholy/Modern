package modern.service.impl;

import modern.exceptions.CouldNotDeleteRecordException;
import modern.exceptions.CouldNotUpdateRecordException;
import modern.exceptions.MissingRequiredFieldException;
import modern.exceptions.NoRecordFoundException;
import modern.io.dao.collageDAO;
import modern.service.collageService;
import modern.shared.dto.collageDTO;
import modern.ui.response.sys.ErrorMessages;
import modern.utils.EntityUtils;

import java.util.List;

public class collageServiceImpl implements collageService {
    collageDAO database;
    EntityUtils entityUtils = new EntityUtils();

    public collageServiceImpl(collageDAO database) {
        this.database = database;
    }

    @Override
    public collageDTO create(collageDTO eDTO) {
        if (eDTO.getCode() == null) {
            throw new MissingRequiredFieldException(ErrorMessages.MISSING_REQUIRED_FIELD.name() + "  collage Code is required");
        } else {
            collageDTO returnValue = null;
            // Check if user already exists
//            collageDTO existingRecord = this.getbyCode(eDTO.getCode());
//            if (existingRecord != null) {
//                throw new CouldNotCreateRecordException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
//            }

            String Id = entityUtils.generateEntityId(30);
            eDTO.setId(Id);
            // Record data into a database
            returnValue = this.save(eDTO);
            // Return back the user profile
            return returnValue;
        }
    }

    @Override
    public collageDTO getbyId(String id) {
        collageDTO returnValue = null;
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
    public collageDTO getbyCode(String code) {
        collageDTO collageDTO = null;

        if (code == null || code.isEmpty()) {
            return collageDTO;
        }

        try {
            this.database.openConnection();
            collageDTO = this.database.getByCode(code);
        } finally {
            this.database.closeConnection();
        }

        return collageDTO;
    }

    @Override
    public List<collageDTO> getAll(int start, int limit) {
        List<collageDTO> collageDTOS = null;

        // Get users from database
        try {
            this.database.openConnection();
            collageDTOS = this.database.getAll(start, limit);
        } finally {
            this.database.closeConnection();
        }

        return collageDTOS;
    }

    @Override
    public void update(collageDTO record) {
        if (record.getId() == null) {
            throw new MissingRequiredFieldException(ErrorMessages.MISSING_REQUIRED_FIELD.name() + "Company ID is required");
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
    public void delete(collageDTO record) {
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

    private collageDTO save(collageDTO eDTO) {
        collageDTO returnValue = null;
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
