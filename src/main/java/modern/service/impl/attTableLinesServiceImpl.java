package modern.service.impl;

import modern.exceptions.*;
import modern.io.dao.attTableLinesDAO;
import modern.io.entity.attTable;
import modern.io.entity.registrationLines;
import modern.io.entity.student;
import modern.service.*;
import modern.shared.dto.*;
import modern.ui.response.sys.ErrorMessages;
import modern.utils.EntityUtils;
import org.springframework.beans.BeanUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class attTableLinesServiceImpl implements attTableLinesService {
    attTableLinesDAO database;
    studentService studentService;
    registrationService registrationService;
    locationService locationService;
    attTableService attTableService;
    EntityUtils entityUtils = new EntityUtils();
    studentDTO student;
    locationDTO location;
    registrationDTO registration;
    attTableDTO attTableDTO;
    public attTableLinesServiceImpl(attTableLinesDAO database, modern.service.studentService studentService, modern.service.registrationService registrationService, modern.service.locationService locationService, modern.service.attTableService attTableService) {
        this.database = database;
        this.studentService = studentService;
        this.registrationService = registrationService;
        this.locationService = locationService;
        this.attTableService = attTableService;
    }

    @Override
    public attTableLinesDTO create(attTableLinesDTO record) throws ParseException {
        String readerMacAddress = record.getReaderMacAddress();

        String qrdetails = record.getHashedQR();
        ZoneId zone = ZoneId.of("Africa/Cairo");

        String encoded = entityUtils.decode(qrdetails);
        int screenshootStatus = Integer.parseInt(encoded.substring(0, Math.min(encoded.length(), 1)));
        String MACADDRESS = encoded.substring(2, Math.min(encoded.length(), 19));
        String DATE = encoded.substring(20, Math.min(encoded.length(), 30));
        String Time = encoded.substring(31, Math.min(encoded.length(), 39));
        String DateTime = encoded.substring(20, Math.min(encoded.length(), 39));
        String studentCode = encoded.substring(40, Math.min(encoded.length(), 1000));
        ZonedDateTime datetime = new SimpleDateFormat("yyyy-MM-dd:kk:mm:ss").parse(DateTime).toInstant().atZone(zone);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(DATE);
        Date dateFromMessage = formatter.parse(formatter.format(date));
        Date todayWithZeroTime = formatter.parse(formatter.format(today));
        Date readingDateTime = record.getReadingDateTime();
        Duration limit = Duration.ofMinutes(10);
        Duration duration;
        ZonedDateTime stop = Instant.now().atZone(zone);
        if (record.getReadingDateTime() != null) {
            ZonedDateTime todate = readingDateTime.toInstant().atZone(zone);
            duration = Duration.between(datetime, todate);
        } else {
            duration = Duration.between(datetime, stop);
        }
        Boolean exceededLimit = (duration.compareTo(limit) > 0);

        if (dateFromMessage.equals(todayWithZeroTime) && exceededLimit.equals(false) && screenshootStatus == 1) {

            this.student = this.studentService.getbyCode(studentCode);
            if (this.student.getMacAddress().equals(MACADDRESS)) {
                this.location = this.locationService.getbyMacAddress(readerMacAddress);
                this.registration = this.registrationService.getbyStudentId(this.student.getId(), date);
                Collection<registrationLines> registrations = this.registration.getLines();
                for (int i = 0; i < registrations.size(); i++) {
                    String subjectId = registrations.iterator().next().getTimeTable().getSubject().getId();
                    String locationId = registrations.iterator().next().getTimeTable().getLocation().getId();
                    String semesterId = registrations.iterator().next().getTimeTable().getSemester().getId();
                    Date semesterFromDate = registrations.iterator().next().getTimeTable().getSemester().getFromDate();
                    Date semesterToDate = registrations.iterator().next().getTimeTable().getSemester().getToDate();
                    int period = registrations.iterator().next().getTimeTable().getPeriod();
                    this.attTableDTO = this.attTableService.getBy4Fields(date, locationId, subjectId, period);
                    record.setDate(date);
                    record.setCode(qrdetails);
                    record.setSubject(registrations.iterator().next().getTimeTable().getSubject());
                    record.setTimeTable(registrations.iterator().next().getTimeTable());
                    attTable attTable = new attTable();
                    BeanUtils.copyProperties(this.attTableDTO, attTable);
                    record.setAttTable(attTable);
                    student student = new student();
                    BeanUtils.copyProperties(this.student, student);
                    record.setStudentId(student);

                }


            }

        } else {
            throw new exceededLimitException(ErrorMessages.Time_EXCEDDED.getErrorMessage());
        }
        attTableLinesDTO returnValue = null;
        String id = entityUtils.generateEntityId(30);
        record.setId(id);
        this.save(record);
        returnValue = this.getbyId(record.getId());
////            // Return back the user profile
        return returnValue;
    }

    @Override
    public attTableLinesDTO createFull(attTableLinesDTO record) throws ParseException {
        if (record.getCode() == null) {
            throw new MissingRequiredFieldException(ErrorMessages.MISSING_REQUIRED_FIELD.name() + "  collage Code is required");
        } else {
            attTableLinesDTO returnValue = null;
            // Check if user already exists
//            attTableLinesDTO existingRecord = this.getbyCode(record.getCode());
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
    public attTableLinesDTO getbyId(String id) {
        attTableLinesDTO returnValue = null;
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
    public List<attTableLinesDTO> getRecord(Date date, DayOfWeek dayOfWeek) {
        List<attTableLinesDTO> attTableLinesDTOS = null;

//        // Get users from database
//        try {
//            this.database.openConnection();
//            attTableLinesDTOS = this.database.getAll(start, limit);
//        } finally {
//            this.database.closeConnection();
//        }

        return attTableLinesDTOS;
    }

    @Override
    public List<attTableLinesDTO> getAll(int start, int limit) {
        List<attTableLinesDTO> attTableLinesDTOS = null;

        // Get users from database
        try {
            this.database.openConnection();
            attTableLinesDTOS = this.database.getAll(start, limit);
        } finally {
            this.database.closeConnection();
        }

        return attTableLinesDTOS;
    }

    @Override
    public void update(attTableLinesDTO record) {
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
    public void delete(attTableLinesDTO record) {
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
    public attTableLinesDTO save(attTableLinesDTO record) {
        attTableLinesDTO returnValue = null;
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
