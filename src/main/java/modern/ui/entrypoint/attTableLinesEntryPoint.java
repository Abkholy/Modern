package modern.ui.entrypoint;

import modern.annotations.Secured;
import modern.service.attTableLinesService;
import modern.shared.dto.attTableLinesDTO;
import modern.ui.request.attTableLinesRequest;
import modern.ui.response.attTableLinesResponse;
import modern.ui.response.sys.DeleteResponseModel;
import modern.ui.response.sys.RequestOperation;
import modern.ui.response.sys.ResponseStatus;
import modern.utils.EntityUtils;
import modern.utils.OptionsResource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Path("/attTableLines")
public class attTableLinesEntryPoint extends OptionsResource {
    @Autowired
    attTableLinesService tableService;

    EntityUtils entityUtils;


    @Secured
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public attTableLinesResponse create(attTableLinesRequest requestObject) throws ParseException {

        attTableLinesResponse returnValue = new attTableLinesResponse();
        // Prepare UserDTO
        attTableLinesDTO bDTO = new attTableLinesDTO();

        BeanUtils.copyProperties(requestObject, bDTO);

//        // Create new
        attTableLinesDTO createdRecord = tableService.createFull(bDTO);

//        //Prepare response
        BeanUtils.copyProperties(createdRecord, returnValue);

        return returnValue;
    }


    @Secured
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<attTableLinesResponse> getAll(@DefaultValue("0") @QueryParam("start") int start,
                                              @DefaultValue("100") @QueryParam("limit") int limit) throws ParseException {
        List<attTableLinesDTO> attendanceTableDTOS = tableService.getAll(start, limit);
        // Prepare return value
        List<attTableLinesResponse> returnValue = new ArrayList<>();
        for (attTableLinesDTO attendanceTableDTO : attendanceTableDTOS) {
            attTableLinesResponse branchModel = new attTableLinesResponse();
            BeanUtils.copyProperties(attendanceTableDTO, branchModel);
            System.out.println(branchModel);
            returnValue.add(branchModel);
        }
        return returnValue;
    }


    @Secured
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public attTableLinesResponse get(@PathParam("id") String id) throws ParseException {
        attTableLinesResponse returnValue = null;

        attTableLinesDTO attendanceTableDTO = tableService.getbyId(id);

        returnValue = new attTableLinesResponse();
        BeanUtils.copyProperties(attendanceTableDTO, returnValue);
        return returnValue;
    }


    @Secured
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public attTableLinesResponse update(@PathParam("id") String id,
                                        attTableLinesRequest details) throws ParseException {
        // Prepare UserDTO

        attTableLinesDTO storedDetails = tableService.getbyId(id);

        // Set only those fields you would like to be updated with this request
        if (details.getAttTable() != null) {
            storedDetails.setAttTable(details.getAttTable());
        }
        storedDetails.setStudent(details.getStudent());
        storedDetails.setSubject(details.getSubject());
        storedDetails.setTimeTable(details.getTimeTable());
        storedDetails.setCode(details.getCode());
        storedDetails.setScannerMacAddress(details.getScannerMacAddress());
        storedDetails.setDate(details.getDate());

        // Update User Details
        tableService.update(storedDetails);

        // Prepare return value
        attTableLinesResponse returnValue = new attTableLinesResponse();
        BeanUtils.copyProperties(storedDetails, returnValue);


        return returnValue;
    }


    @Secured
    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public DeleteResponseModel delete(@PathParam("id") String id) throws ParseException {
        DeleteResponseModel returnValue = new DeleteResponseModel();
        returnValue.setRequestOperation(RequestOperation.DELETE);

        attTableLinesDTO storedRecord = tableService.getbyId(id);

        tableService.delete(storedRecord);

        returnValue.setResponseStatus(ResponseStatus.SUCCESS);

        return returnValue;
    }

}
