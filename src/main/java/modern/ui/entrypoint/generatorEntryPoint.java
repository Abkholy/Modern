package modern.ui.entrypoint;

import modern.annotations.Secured;
import modern.exceptions.IllegalMACException;
import modern.service.studentService;
import modern.shared.dto.studentDTO;
import modern.ui.request.generatorRequest;
import modern.ui.response.studentResponse;
import modern.ui.response.sys.ErrorMessages;
import modern.utils.EntityUtils;
import modern.utils.OptionsResource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;

@Path("/generator")
public class generatorEntryPoint extends OptionsResource {
    @Autowired
    studentService studentService;

    EntityUtils entityUtils;


    @Secured
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public studentResponse create(generatorRequest requestObject) throws ParseException {

        studentResponse returnValue = new studentResponse();
        // Prepare UserDTO
        studentDTO bDTO = new studentDTO();

        BeanUtils.copyProperties(requestObject, bDTO);

//        // Create new
        studentDTO createdRecord = studentService.getbyCodeAndMacAddress(bDTO.getCode(), bDTO.getMacAddress());

//        //Prepare response
        BeanUtils.copyProperties(createdRecord, returnValue);

        return returnValue;
    }

    @Secured
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public studentResponse update(generatorRequest details) throws ParseException {
        // Prepare UserDTO

        String code = details.getCode();
        studentDTO storedDetails = studentService.getbyCode(code);

        // Set only those fields you would like to be updated with this request
        if (details.getCode() != null && !details.getCode().isEmpty()) {
            storedDetails.setCode(details.getCode());
        }
        if (storedDetails.getMacAddress().equals(null) || storedDetails.getMacAddress().equals("")) {
            if (!storedDetails.getMacAddress().equals(details.getMacAddress()) && !storedDetails.getMacAddress().equals("")) {
                throw new IllegalMACException(ErrorMessages.MAC_ADDRESS_ILLEGAL.getErrorMessage());
            } else {
                storedDetails.setMacAddress(details.getMacAddress());
            }
        } else {
            throw new IllegalMACException(ErrorMessages.MAC_ADDRESS_ILLEGAL.getErrorMessage());
        }
        // Update User Details
        studentService.update(storedDetails);

        // Prepare return value
        studentResponse returnValue = new studentResponse();
        BeanUtils.copyProperties(storedDetails, returnValue);

        return returnValue;
    }
}
