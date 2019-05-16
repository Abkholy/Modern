package modern.ui.entrypoint;

import modern.annotations.Secured;
import modern.service.registrationService;
import modern.shared.dto.registrationDTO;
import modern.ui.request.registrationRequest;
import modern.ui.response.registrationResponse;
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

@Path("/registration")
public class registrationEntryPoint extends OptionsResource {

    @Autowired
    registrationService registrationService;

    @Secured
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public registrationResponse create(registrationRequest requestObject) {
        registrationResponse returnValue = new registrationResponse();
        // Prepare UserDTO
        registrationDTO bDTO = new registrationDTO();

        BeanUtils.copyProperties(requestObject, bDTO);

//        // Create new
        registrationDTO createdRecord = registrationService.create(bDTO);

//        //Prepare response
        BeanUtils.copyProperties(createdRecord, returnValue);

        return returnValue;
    }


    @Secured
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<registrationResponse> getAll(@DefaultValue("0") @QueryParam("start") int start,
                                             @DefaultValue("100") @QueryParam("limit") int limit) {
        List<registrationDTO> registrationDTOS = registrationService.getAll(start, limit);
        // Prepare return value
        List<registrationResponse> returnValue = new ArrayList<registrationResponse>();
        for (registrationDTO registrationDTO : registrationDTOS) {
            registrationResponse branchModel = new registrationResponse();
            BeanUtils.copyProperties(registrationDTO, branchModel);
            System.out.println(branchModel);
            returnValue.add(branchModel);

        }
        return returnValue;
    }


    @Secured
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public registrationResponse get(@PathParam("id") String id) {
        registrationResponse returnValue = null;

        registrationDTO branch = registrationService.getbyId(id);

        returnValue = new registrationResponse();
        BeanUtils.copyProperties(branch, returnValue);

        return returnValue;
    }


    @Secured
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public registrationResponse update(@PathParam("id") String id,
                                       registrationRequest details) throws ParseException {
        // Prepare UserDTO

        registrationDTO storedDetails = registrationService.getbyId(id);

        // Set only those fields you would like to be updated with this request
        if (details.getCode() != null && !details.getCode().isEmpty()) {
            storedDetails.setCode(details.getCode());
        }
        storedDetails.setLevel(details.getLevel());
        storedDetails.setTerm(details.getTerm());
        storedDetails.setDetails(details.getDetails());
        storedDetails.setFromDate(details.getFromDate());
        storedDetails.setToDate(details.getToDate());
        // Update User Details
        registrationService.update(storedDetails);

        // Prepare return value
        registrationResponse returnValue = new registrationResponse();
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


        registrationDTO storedRecord = registrationService.getbyId(id);

        registrationService.delete(storedRecord);

        returnValue.setResponseStatus(ResponseStatus.SUCCESS);

        return returnValue;
    }

}