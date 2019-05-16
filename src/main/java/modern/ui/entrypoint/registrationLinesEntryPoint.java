package modern.ui.entrypoint;

import modern.annotations.Secured;
import modern.service.registrationLinesService;
import modern.shared.dto.registrationLinesDTO;
import modern.ui.request.registrationLinesRequest;
import modern.ui.response.registrationLinesResponse;
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

@Path("/registrationLines")
public class registrationLinesEntryPoint extends OptionsResource {

    @Autowired
    registrationLinesService registrationLinesService;

    @Secured
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public registrationLinesResponse create(registrationLinesRequest requestObject) {
        registrationLinesResponse returnValue = new registrationLinesResponse();
        // Prepare UserDTO
        registrationLinesDTO bDTO = new registrationLinesDTO();

        BeanUtils.copyProperties(requestObject, bDTO);

//        // Create new
        registrationLinesDTO createdRecord = registrationLinesService.create(bDTO);

//        //Prepare response
        BeanUtils.copyProperties(createdRecord, returnValue);

        return returnValue;
    }


    @Secured
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<registrationLinesResponse> getAll(@DefaultValue("0") @QueryParam("start") int start,
                                                  @DefaultValue("100") @QueryParam("limit") int limit) {
        List<registrationLinesDTO> registrationLinesDTOS = registrationLinesService.getAll(start, limit);
        // Prepare return value
        List<registrationLinesResponse> returnValue = new ArrayList<registrationLinesResponse>();
        for (registrationLinesDTO registrationLinesDTO : registrationLinesDTOS) {
            registrationLinesResponse branchModel = new registrationLinesResponse();
            BeanUtils.copyProperties(registrationLinesDTO, branchModel);
            System.out.println(branchModel);
            returnValue.add(branchModel);

        }
        return returnValue;
    }


    @Secured
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public registrationLinesResponse get(@PathParam("id") String id) {
        registrationLinesResponse returnValue = null;

        registrationLinesDTO branch = registrationLinesService.getbyId(id);

        returnValue = new registrationLinesResponse();
        BeanUtils.copyProperties(branch, returnValue);

        return returnValue;
    }


    @Secured
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public registrationLinesResponse update(@PathParam("id") String id,
                                            registrationLinesRequest details) throws ParseException {
        // Prepare UserDTO

        registrationLinesDTO storedDetails = registrationLinesService.getbyId(id);

        // Set only those fields you would like to be updated with this request
        storedDetails.setSubject(details.getSubject());
        storedDetails.setRegistration(details.getRegistration());
        storedDetails.setTimeTable(details.getTimeTable());


        // Update User Details
        registrationLinesService.update(storedDetails);

        // Prepare return value
        registrationLinesResponse returnValue = new registrationLinesResponse();
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

        registrationLinesDTO storedRecord = registrationLinesService.getbyId(id);

        registrationLinesService.delete(storedRecord);

        returnValue.setResponseStatus(ResponseStatus.SUCCESS);

        return returnValue;
    }

}