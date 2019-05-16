package modern.ui.entrypoint;

import modern.annotations.Secured;
import modern.service.studentService;
import modern.shared.dto.studentDTO;
import modern.ui.request.studentRequest;
import modern.ui.response.studentResponse;
import modern.ui.response.sys.DeleteResponseModel;
import modern.ui.response.sys.RequestOperation;
import modern.ui.response.sys.ResponseStatus;
import modern.utils.OptionsResource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Path("/student")
public class studentEntryPoint extends OptionsResource {

    @Autowired
    studentService studentService;

    @Secured
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public studentResponse create(studentRequest requestObject) {
        studentResponse returnValue = new studentResponse();
        // Prepare UserDTO
        studentDTO bDTO = new studentDTO();

        BeanUtils.copyProperties(requestObject, bDTO);

//        // Create new
        studentDTO createdRecord = studentService.create(bDTO);

//        //Prepare response
        BeanUtils.copyProperties(createdRecord, returnValue);

        return returnValue;
    }


    @Secured
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<studentResponse> getAll(@DefaultValue("0") @QueryParam("start") int start,
                                        @DefaultValue("100") @QueryParam("limit") int limit) {
        List<studentDTO> studentDTOS = studentService.getAll(start, limit);
        // Prepare return value
        List<studentResponse> returnValue = new ArrayList<studentResponse>();
        for (studentDTO studentDTO : studentDTOS) {
            studentResponse branchModel = new studentResponse();
            BeanUtils.copyProperties(studentDTO, branchModel);
            System.out.println(branchModel);
            returnValue.add(branchModel);

        }
        return returnValue;
    }


    @Secured
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public studentResponse get(@PathParam("id") String id) {
        studentResponse returnValue = null;

        studentDTO branch = studentService.getbyId(id);

        returnValue = new studentResponse();
        BeanUtils.copyProperties(branch, returnValue);

        return returnValue;
    }


    @Secured
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public studentResponse update(@PathParam("id") String id,
                                  studentRequest details) throws ParseException {
        // Prepare UserDTO

        studentDTO storedDetails = studentService.getbyId(id);

        // Set only those fields you would like to be updated with this request
        if (details.getCode() != null && !details.getCode().isEmpty()) {
            storedDetails.setCode(details.getCode());
        }
        storedDetails.setEmail(details.getEmail());
        storedDetails.setImage(details.getImage());
        storedDetails.setName(details.getName());
        storedDetails.setPhoneNumber(details.getPhoneNumber());
        storedDetails.setLevel(details.getLevel());
        storedDetails.setMacAddress(details.getMacAddress());
        storedDetails.setSsn(details.getSsn());
        storedDetails.setCollage(details.getCollage());
        // Update User Details
        studentService.update(storedDetails);

        // Prepare return value
        studentResponse returnValue = new studentResponse();
        BeanUtils.copyProperties(storedDetails, returnValue);


        return returnValue;
    }


    @Secured
    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public DeleteResponseModel delete(@PathParam("id") String id) {
        DeleteResponseModel returnValue = new DeleteResponseModel();
        returnValue.setRequestOperation(RequestOperation.DELETE);

        studentDTO storedRecord = studentService.getbyId(id);

        studentService.delete(storedRecord);

        returnValue.setResponseStatus(ResponseStatus.SUCCESS);

        return returnValue;
    }

}