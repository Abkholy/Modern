package modern.ui.entrypoint;


import modern.annotations.Secured;
import modern.service.semesterService;
import modern.shared.dto.semesterDTO;
import modern.ui.request.semesterRequest;
import modern.ui.response.semesterResponse;
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

@Path("/semester")
public class semesterEntryPoint extends OptionsResource {

    @Autowired
    semesterService semesterService;


    @Secured
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public semesterResponse create(semesterRequest requestObject) {
        semesterResponse returnValue = new semesterResponse();
        // Prepare UserDTO
        semesterDTO bDTO = new semesterDTO();

        BeanUtils.copyProperties(requestObject, bDTO);

//        // Create new
        semesterDTO createdRecord = semesterService.create(bDTO);

//        //Prepare response
        BeanUtils.copyProperties(createdRecord, returnValue);

        return returnValue;
    }


    @Secured
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<semesterResponse> getAll(@DefaultValue("0") @QueryParam("start") int start,
                                         @DefaultValue("100") @QueryParam("limit") int limit) {
        List<semesterDTO> semesterDTOS = semesterService.getAll(start, limit);
        // Prepare return value
        List<semesterResponse> returnValue = new ArrayList<semesterResponse>();
        for (semesterDTO semesterDTO : semesterDTOS) {
            semesterResponse branchModel = new semesterResponse();
            BeanUtils.copyProperties(semesterDTO, branchModel);
            System.out.println(branchModel);
            returnValue.add(branchModel);

        }
        return returnValue;
    }


    @Secured
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public semesterResponse get(@PathParam("id") String id) {
        semesterResponse returnValue = null;

        semesterDTO branch = semesterService.getbyId(id);

        returnValue = new semesterResponse();
        BeanUtils.copyProperties(branch, returnValue);

        return returnValue;
    }


    @Secured
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public semesterResponse update(@PathParam("id") String id,
                                   semesterRequest details) throws ParseException {
        // Prepare UserDTO

        semesterDTO storedDetails = semesterService.getbyId(id);

        // Set only those fields you would like to be updated with this request
        if (details.getCode() != null && !details.getCode().isEmpty()) {
            storedDetails.setCode(details.getCode());
        }
        storedDetails.setCollage(details.getCollage());
        storedDetails.setFromDate(details.getFromDate());
        storedDetails.setToDate(details.getToDate());


        // Update User Details
        semesterService.update(storedDetails);

        // Prepare return value
        semesterResponse returnValue = new semesterResponse();
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

        semesterDTO storedRecord = semesterService.getbyId(id);

        semesterService.delete(storedRecord);

        returnValue.setResponseStatus(ResponseStatus.SUCCESS);

        return returnValue;
    }
}