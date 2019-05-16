package modern.service.impl;

import modern.exceptions.*;
import modern.io.dao.studentDAO;
import modern.service.studentService;
import modern.service.usersService;
import modern.shared.dto.studentDTO;
import modern.shared.dto.usersDTO;
import modern.ui.response.sys.ErrorMessages;
import modern.utils.EntityUtils;

import java.util.List;

public class studentServiceImpl implements studentService {
    studentDAO database;
    EntityUtils entityUtils = new EntityUtils();
    usersService usersService;
    usersDTO users;

    public studentServiceImpl(studentDAO database, modern.service.usersService usersService) {
        this.database = database;
        this.usersService = usersService;
    }

    @Override
    public studentDTO create(studentDTO record) {
        if (record.getCode() == null) {
            throw new MissingRequiredFieldException(ErrorMessages.MISSING_REQUIRED_FIELD.name() + "  collage Code is required");
        } else {
            studentDTO returnValue = null;
            // Check if user already exists
//            studentDTO existingRecord = this.getbyCode(record.getCode());
//            if (existingRecord != null) {
//                throw new CouldNotCreateRecordException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
//            }

            String Id = entityUtils.generateEntityId(30);
            record.setId(Id);
            // Record data into a database

            returnValue = this.save(record);
            // Return back the user profile
            usersDTO user = new usersDTO();
            user.setFirstName("student" + record.getName());
            user.setLastName(record.getName());
            user.setEmail(record.getCode());
            user.setPassword(record.getCode() + "-" + record.getPhoneNumber());
            user.setCollage(record.getCollage());
            usersService.create(user);
            return returnValue;
        }
    }

    @Override
    public studentDTO getbyId(String id) {
        studentDTO returnValue = null;
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
    public studentDTO getbyCode(String code) {
        studentDTO studentDTO = null;

        if (code == null || code.isEmpty()) {
            return studentDTO;
        }

        try {
            this.database.openConnection();
            studentDTO = this.database.getByCode(code);
        } finally {
            this.database.closeConnection();
        }

        return studentDTO;
    }

    @Override
    public studentDTO getbyCodeAndMacAddress(String code, String mac) {
        studentDTO studentDTO = null;

        if (code == null || code.isEmpty()) {
            return studentDTO;
        }

        try {
            this.database.openConnection();
            studentDTO = this.database.getByCodeAndMac(code, mac);
            if (!studentDTO.getMacAddress().equals(mac) && !studentDTO.getCode().equals(code)) {
                throw new IllegalMACException(ErrorMessages.MAC_ADDRESS_ILLEGAL.getErrorMessage());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalMACException(ErrorMessages.MAC_ADDRESS_ILLEGAL.getErrorMessage());
        } finally {
            this.database.closeConnection();
        }

        return studentDTO;
    }

    @Override
    public List<studentDTO> getAll(int start, int limit) {
        List<studentDTO> studentDTOS = null;

        // Get users from database
        try {
            this.database.openConnection();
            studentDTOS = this.database.getAll(start, limit);
        } finally {
            this.database.closeConnection();
        }

        return studentDTOS;
    }

    @Override
    public void update(studentDTO record) {
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
    public void delete(studentDTO record) {
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
    public studentDTO save(studentDTO record) {
        studentDTO returnValue = null;
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
