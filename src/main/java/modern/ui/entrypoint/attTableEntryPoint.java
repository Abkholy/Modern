package modern.ui.entrypoint;

import modern.annotations.Secured;
import modern.service.attTableService;
import modern.shared.dto.attTableDTO;
import modern.ui.request.attTableRquest;
import modern.ui.response.attTableResponse;
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

@Path("/attTable")
public class attTableEntryPoint extends OptionsResource {
    @Autowired
    attTableService tableService;

    EntityUtils entityUtils;


    @Secured
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public attTableResponse create(attTableRquest requestObject) throws ParseException {

        attTableResponse returnValue = new attTableResponse();
        // Prepare UserDTO
        attTableDTO bDTO = new attTableDTO();

        BeanUtils.copyProperties(requestObject, bDTO);


//        // Create new
        attTableDTO createdRecord = tableService.create(bDTO);

//        //Prepare response
        BeanUtils.copyProperties(createdRecord, returnValue);

        return returnValue;
    }


    @Secured
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<attTableResponse> getAll(@DefaultValue("0") @QueryParam("start") int start,
                                         @DefaultValue("100") @QueryParam("limit") int limit) {
        List<attTableDTO> attTableDTOS = tableService.getAll(start, limit);
        // Prepare return value
        List<attTableResponse> returnValue = new ArrayList<>();
        for (attTableDTO attTableDTO : attTableDTOS) {
            attTableResponse res = new attTableResponse();
            BeanUtils.copyProperties(attTableDTO, res);
            System.out.println(res);
            returnValue.add(res);

        }
        return returnValue;
    }

    @Secured
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public attTableResponse get(@PathParam("id") String id) throws ParseException {
        attTableResponse returnValue = null;

        attTableDTO attTableDTO = tableService.getbyId(id);

        returnValue = new attTableResponse();
        BeanUtils.copyProperties(attTableDTO, returnValue);

        return returnValue;
    }

    @Secured
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public attTableResponse update(@PathParam("id") String id,
                                   attTableRquest details) throws ParseException {
        // Prepare UserDTO

        attTableDTO storedDetails = tableService.getbyId(id);

        // Set only those fields you would like to be updated with this request
        if (details.getCode() != null && !details.getCode().isEmpty()) {
            storedDetails.setCode(details.getCode());
        }
        storedDetails.setDate(details.getDate());
        storedDetails.setDescription(details.getDescription());
        storedDetails.setLocation(details.getLocation());
        storedDetails.setSubject(details.getSubject());

        // Update User Details
        tableService.update(storedDetails);

        // Prepare return value
        attTableResponse returnValue = new attTableResponse();
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

        attTableDTO storedRecord = tableService.getbyId(id);

        tableService.delete(storedRecord);

        returnValue.setResponseStatus(ResponseStatus.SUCCESS);

        return returnValue;
    }

}
