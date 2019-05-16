package modern.service.impl;

import modern.exceptions.CouldNotDeleteRecordException;
import modern.exceptions.CouldNotUpdateRecordException;
import modern.exceptions.MissingRequiredFieldException;
import modern.exceptions.NoRecordFoundException;
import modern.io.dao.registrationDAO;
import modern.service.registrationService;
import modern.service.semesterService;
import modern.shared.dto.registrationDTO;
import modern.shared.dto.semesterDTO;
import modern.ui.response.sys.ErrorMessages;
import modern.utils.EntityUtils;

import java.util.Date;
import java.util.List;

public class registrationServiceImpl implements registrationService {
    registrationDAO database;
    EntityUtils entityUtils = new EntityUtils();
    semesterService semesterService;
    semesterDTO semesterDTO;

    public registrationServiceImpl(registrationDAO database, semesterService semesterService) {
        this.database = database;
        this.semesterService = semesterService;
    }

    @Override
    public registrationDTO create(registrationDTO record) {
        if (record.getCode() == null) {
            throw new MissingRequiredFieldException(ErrorMessages.MISSING_REQUIRED_FIELD.name() + "  collage Code is required");
        } else {
            registrationDTO returnValue = null;
            // Check if user already exists
//            registrationDTO existingRecord = this.getbyCode(record.getCode());
//            if (existingRecord != null) {
//                throw new CouldNotCreateRecordException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
//            }

            String Id = entityUtils.generateEntityId(30);
            record.setId(Id);
            this.semesterDTO = this.semesterService.getbyId(record.getSemester().getId());
            record.setFromDate(this.semesterDTO.getFromDate());
            record.setToDate(this.semesterDTO.getToDate());
            // Record data into a database
            returnValue = this.save(record);
            // Return back the user profile
            return returnValue;
        }
    }

    @Override
    public registrationDTO getbyId(String id) {
        registrationDTO returnValue = null;
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
    public registrationDTO getbyCode(String code) {
        registrationDTO registrationDTO = null;

        if (code == null || code.isEmpty()) {
            return registrationDTO;
        }

        try {
            this.database.openConnection();
            registrationDTO = this.database.getByCode(code);
        } finally {
            this.database.closeConnection();
        }

        return registrationDTO;
    }

    @Override
    public registrationDTO getbyStudentId(String studentId, Date date) {
        registrationDTO registrationDTO = null;

        if (studentId == null || studentId.isEmpty()) {
            return registrationDTO;
        }

        try {
            this.database.openConnection();
            registrationDTO = this.database.getByStudentId(studentId, date);
        } finally {
            this.database.closeConnection();
        }

        return registrationDTO;
    }

    @Override
    public List<registrationDTO> getAll(int start, int limit) {
        List<registrationDTO> registrationDTOS = null;

        // Get users from database
        try {
            this.database.openConnection();
            registrationDTOS = this.database.getAll(start, limit);
        } finally {
            this.database.closeConnection();
        }

        return registrationDTOS;
    }

    @Override
    public void update(registrationDTO record) {
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
    public void delete(registrationDTO record) {
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
    public registrationDTO save(registrationDTO record) {
        registrationDTO returnValue = null;
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
