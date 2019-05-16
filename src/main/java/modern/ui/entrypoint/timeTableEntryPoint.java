package modern.ui.entrypoint;

import modern.annotations.Secured;
import modern.service.timeTableService;
import modern.shared.dto.timeTableDTO;
import modern.ui.request.timeTableRequest;
import modern.ui.response.sys.DeleteResponseModel;
import modern.ui.response.sys.RequestOperation;
import modern.ui.response.sys.ResponseStatus;
import modern.ui.response.timeTableResponse;
import modern.utils.OptionsResource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@Path("/timeTable")
public class timeTableEntryPoint extends OptionsResource {

    @Autowired
    timeTableService timeTableService;

    @Secured
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public timeTableResponse create(timeTableRequest requestObject) throws ParseException {
        timeTableResponse returnValue = new timeTableResponse();
        // Prepare UserDTO
        timeTableDTO bDTO = new timeTableDTO();

        BeanUtils.copyProperties(requestObject, bDTO);

//        // Create new
        timeTableDTO createdRecord = timeTableService.create(bDTO);

//        //Prepare response
        BeanUtils.copyProperties(createdRecord, returnValue);

        return returnValue;
    }


    @Secured
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<timeTableResponse> getAll(@DefaultValue("0") @QueryParam("start") int start,
                                          @DefaultValue("100") @QueryParam("limit") int limit) {
        List<timeTableDTO> timeTableDTOS = timeTableService.getAll(start, limit);
        // Prepare return value
        List<timeTableResponse> returnValue = new ArrayList<timeTableResponse>();
        for (timeTableDTO timeTableDTO : timeTableDTOS) {
            timeTableResponse branchModel = new timeTableResponse();
            BeanUtils.copyProperties(timeTableDTO, branchModel);
            System.out.println(branchModel);
            returnValue.add(branchModel);

        }
        return returnValue;
    }


    @Secured
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public timeTableResponse get(@PathParam("id") String id) {
        timeTableResponse returnValue = null;

        timeTableDTO branch = timeTableService.getbyId(id);

        returnValue = new timeTableResponse();
        BeanUtils.copyProperties(branch, returnValue);

        return returnValue;
    }


    @Secured
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public timeTableResponse update(@PathParam("id") String id,
                                    timeTableRequest details) throws ParseException {
        // Prepare UserDTO

        timeTableDTO storedDetails = timeTableService.getbyId(id);

        // Set only those fields you would like to be updated with this request
        if (details.getCode() != null && !details.getCode().isEmpty()) {
            storedDetails.setCode(details.getCode());
        }
        storedDetails.setLocation(details.getLocation());
        storedDetails.setDayOfWeek(details.getDayOfWeek());
        storedDetails.setLength(details.getLength());
        storedDetails.setSubject(details.getSubject());
        // Update User Details
        timeTableService.update(storedDetails);

        // Prepare return value
        timeTableResponse returnValue = new timeTableResponse();
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

        timeTableDTO storedRecord = timeTableService.getbyId(id);

        timeTableService.delete(storedRecord);

        returnValue.setResponseStatus(ResponseStatus.SUCCESS);

        return returnValue;
    }

}