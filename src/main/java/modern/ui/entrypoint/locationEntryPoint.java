package modern.ui.entrypoint;

import modern.annotations.Secured;
import modern.service.locationService;
import modern.shared.dto.locationDTO;
import modern.ui.request.locationRequest;
import modern.ui.response.locationResponse;
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

@Path("/location")
public class locationEntryPoint extends OptionsResource {

    @Autowired
    locationService locationService;

    @Secured
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public locationResponse create(locationRequest requestObject) {
        locationResponse returnValue = new locationResponse();
        // Prepare UserDTO
        locationDTO bDTO = new locationDTO();

        BeanUtils.copyProperties(requestObject, bDTO);

//        // Create new
        locationDTO createdRecord = locationService.create(bDTO);

//        //Prepare response
        BeanUtils.copyProperties(createdRecord, returnValue);

        return returnValue;
    }


    @Secured
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<locationResponse> getAll(@DefaultValue("0") @QueryParam("start") int start,
                                         @DefaultValue("100") @QueryParam("limit") int limit) {
        List<locationDTO> locationDTOS = locationService.getAll(start, limit);
        // Prepare return value
        List<locationResponse> returnValue = new ArrayList<locationResponse>();
        for (locationDTO locationDTO : locationDTOS) {
            locationResponse branchModel = new locationResponse();
            BeanUtils.copyProperties(locationDTO, branchModel);
            System.out.println(branchModel);
            returnValue.add(branchModel);

        }
        return returnValue;
    }


    @Secured
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public locationResponse get(@PathParam("id") String id) {
        locationResponse returnValue = null;

        locationDTO branch = locationService.getbyId(id);

        returnValue = new locationResponse();
        BeanUtils.copyProperties(branch, returnValue);

        return returnValue;
    }


    @Secured
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public locationResponse update(@PathParam("id") String id,
                                   locationRequest details) throws ParseException {
        // Prepare UserDTO

        locationDTO storedDetails = locationService.getbyId(id);

        // Set only those fields you would like to be updated with this request
        if (details.getCode() != null && !details.getCode().isEmpty()) {
            storedDetails.setCode(details.getCode());
        }
        storedDetails.setCapacity(details.getCapacity());
        storedDetails.setCollage(details.getCollage());
        storedDetails.setName(details.getName());
        storedDetails.setScannerMacAddress(details.getScannerMacAddress());
        // Update User Details
        locationService.update(storedDetails);

        // Prepare return value
        locationResponse returnValue = new locationResponse();
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

        locationDTO storedRecord = locationService.getbyId(id);

        locationService.delete(storedRecord);

        returnValue.setResponseStatus(ResponseStatus.SUCCESS);

        return returnValue;
    }
}
