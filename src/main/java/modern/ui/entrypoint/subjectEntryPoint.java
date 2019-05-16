package modern.ui.entrypoint;

import modern.annotations.Secured;
import modern.service.subjectService;
import modern.shared.dto.subjectDTO;
import modern.ui.request.subjectRequest;
import modern.ui.response.subjectResponse;
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

@Path("/subject")
public class subjectEntryPoint extends OptionsResource {

    @Autowired
    subjectService subjectService;

    @Secured
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public subjectResponse create(subjectRequest requestObject) {
        subjectResponse returnValue = new subjectResponse();
        // Prepare UserDTO
        subjectDTO bDTO = new subjectDTO();

        BeanUtils.copyProperties(requestObject, bDTO);

//        // Create new
        subjectDTO createdRecord = subjectService.create(bDTO);

//        //Prepare response
        BeanUtils.copyProperties(createdRecord, returnValue);

        return returnValue;
    }


    @Secured
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<subjectResponse> getAll(@DefaultValue("0") @QueryParam("start") int start,
                                        @DefaultValue("100") @QueryParam("limit") int limit) {
        List<subjectDTO> subjectDTOS = subjectService.getAll(start, limit);
        // Prepare return value
        List<subjectResponse> returnValue = new ArrayList<subjectResponse>();
        for (subjectDTO subjectDTO : subjectDTOS) {
            subjectResponse branchModel = new subjectResponse();
            BeanUtils.copyProperties(subjectDTO, branchModel);
            System.out.println(branchModel);
            returnValue.add(branchModel);

        }
        return returnValue;
    }


    @Secured
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public subjectResponse get(@PathParam("id") String id) {
        subjectResponse returnValue = null;

        subjectDTO branch = subjectService.getbyId(id);

        returnValue = new subjectResponse();
        BeanUtils.copyProperties(branch, returnValue);

        return returnValue;
    }


    @Secured
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public subjectResponse update(@PathParam("id") String id,
                                  subjectRequest details) throws ParseException {
        // Prepare UserDTO

        subjectDTO storedDetails = subjectService.getbyId(id);

        // Set only those fields you would like to be updated with this request
        if (details.getCode() != null && !details.getCode().isEmpty()) {
            storedDetails.setCode(details.getCode());
        }
        storedDetails.setDefaultInstructor(details.getDefaultInstructor());
        storedDetails.setDescription(details.getDescription());
        storedDetails.setName(details.getName());
        storedDetails.setFiles(details.getFiles());
        storedDetails.setCreditHours(details.getCreditHours());
        // Update User Details
        subjectService.update(storedDetails);

        // Prepare return value
        subjectResponse returnValue = new subjectResponse();
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

        subjectDTO storedRecord = subjectService.getbyId(id);

        subjectService.delete(storedRecord);

        returnValue.setResponseStatus(ResponseStatus.SUCCESS);

        return returnValue;
    }

}