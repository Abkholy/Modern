package modern.ui.entrypoint;

import modern.annotations.Secured;
import modern.service.collageService;
import modern.shared.dto.collageDTO;
import modern.ui.request.collageRequest;
import modern.ui.response.collageResponse;
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

@Path("/collage")
public class collageEntryPoint extends OptionsResource {

    @Autowired
    collageService collageService;

    @Secured
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public collageResponse create(collageRequest requestObject) {
        collageResponse returnValue = new collageResponse();
        // Prepare UserDTO
        collageDTO bDTO = new collageDTO();

        BeanUtils.copyProperties(requestObject, bDTO);

//        // Create new
        collageDTO createdRecord = collageService.create(bDTO);

//        //Prepare response
        BeanUtils.copyProperties(createdRecord, returnValue);

        return returnValue;
    }


    @Secured
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<collageResponse> getAll(@DefaultValue("0") @QueryParam("start") int start,
                                        @DefaultValue("100") @QueryParam("limit") int limit) {
        List<collageDTO> collageDTOS = collageService.getAll(start, limit);
        // Prepare return value
        List<collageResponse> returnValue = new ArrayList<collageResponse>();
        for (collageDTO collageDTO : collageDTOS) {
            collageResponse branchModel = new collageResponse();
            BeanUtils.copyProperties(collageDTO, branchModel);
            System.out.println(branchModel);
            returnValue.add(branchModel);

        }
        return returnValue;
    }


    @Secured
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public collageResponse get(@PathParam("id") String id) {
        collageResponse returnValue = null;

        collageDTO branch = collageService.getbyId(id);

        returnValue = new collageResponse();
        BeanUtils.copyProperties(branch, returnValue);

        return returnValue;
    }


    @Secured
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public collageResponse update(@PathParam("id") String id,
                                  collageRequest details) throws ParseException {
        // Prepare UserDTO

        collageDTO storedDetails = collageService.getbyId(id);

        // Set only those fields you would like to be updated with this request
        if (details.getCode() != null && !details.getCode().isEmpty()) {
            storedDetails.setCode(details.getCode());
        }
        storedDetails.setEmail(details.getEmail());
        storedDetails.setLocation(details.getLocation());
        storedDetails.setName(details.getName());
        storedDetails.setPhoneNumber(details.getPhoneNumber());
        storedDetails.setWebsite(details.getWebsite());
        // Update User Details
        collageService.update(storedDetails);

        // Prepare return value
        collageResponse returnValue = new collageResponse();
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

        collageDTO storedRecord = collageService.getbyId(id);

        collageService.delete(storedRecord);

        returnValue.setResponseStatus(ResponseStatus.SUCCESS);

        return returnValue;
    }

}
