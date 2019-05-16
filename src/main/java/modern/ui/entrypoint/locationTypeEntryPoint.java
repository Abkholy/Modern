package modern.ui.entrypoint;

import modern.annotations.Secured;
import modern.service.locationTypeService;
import modern.shared.dto.locationTypeDTO;
import modern.ui.request.locationTypeRequest;
import modern.ui.response.locationTypeResponse;
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

@Path("/locationType")
public class locationTypeEntryPoint extends OptionsResource {

    @Autowired
    locationTypeService locationTypeService;


    @Secured
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public locationTypeResponse create(locationTypeRequest requestObject) {
        locationTypeResponse returnValue = new locationTypeResponse();
        // Prepare UserDTO
        locationTypeDTO bDTO = new locationTypeDTO();

        BeanUtils.copyProperties(requestObject, bDTO);

//        // Create new
        locationTypeDTO createdRecord = locationTypeService.create(bDTO);

//        //Prepare response
        BeanUtils.copyProperties(createdRecord, returnValue);

        return returnValue;
    }


    @Secured
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<locationTypeResponse> getAll(@DefaultValue("0") @QueryParam("start") int start,
                                             @DefaultValue("100") @QueryParam("limit") int limit) {
        List<locationTypeDTO> locationTypeDTOS = locationTypeService.getAll(start, limit);
        // Prepare return value
        List<locationTypeResponse> returnValue = new ArrayList<locationTypeResponse>();
        for (locationTypeDTO locationTypeDTO : locationTypeDTOS) {
            locationTypeResponse branchModel = new locationTypeResponse();
            BeanUtils.copyProperties(locationTypeDTO, branchModel);
            System.out.println(branchModel);
            returnValue.add(branchModel);

        }
        return returnValue;
    }


    @Secured
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public locationTypeResponse get(@PathParam("id") String id) {
        locationTypeResponse returnValue = null;

        locationTypeDTO branch = locationTypeService.getbyId(id);

        returnValue = new locationTypeResponse();
        BeanUtils.copyProperties(branch, returnValue);

        return returnValue;
    }


    @Secured
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public locationTypeResponse update(@PathParam("id") String id,
                                       locationTypeRequest details) throws ParseException {
        // Prepare UserDTO

        locationTypeDTO storedDetails = locationTypeService.getbyId(id);

        // Set only those fields you would like to be updated with this request
        if (details.getCode() != null && !details.getCode().isEmpty()) {
            storedDetails.setCode(details.getCode());
        }
        storedDetails.setName(details.getName());


        // Update User Details
        locationTypeService.update(storedDetails);

        // Prepare return value
        locationTypeResponse returnValue = new locationTypeResponse();
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

        locationTypeDTO storedRecord = locationTypeService.getbyId(id);

        locationTypeService.delete(storedRecord);

        returnValue.setResponseStatus(ResponseStatus.SUCCESS);

        return returnValue;
    }
}
