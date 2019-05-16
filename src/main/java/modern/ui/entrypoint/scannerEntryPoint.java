package modern.ui.entrypoint;

import modern.annotations.Secured;
import modern.service.attTableLinesService;
import modern.ui.request.attTableReaderRequest;
import modern.ui.response.attTableLinesResponse;
import modern.utils.EntityUtils;
import modern.utils.OptionsResource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;

@Path("/scanner")
public class scannerEntryPoint extends OptionsResource {
    @Autowired
    attTableLinesService tableService;

    EntityUtils entityUtils;

    @Secured
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public attTableLinesResponse create(attTableReaderRequest requestObject) throws ParseException {

        attTableLinesResponse returnValue = new attTableLinesResponse();
        // Prepare UserDTO
//        attTableLinesDTO bDTO = new attTableLinesDTO();
//
//        BeanUtils.copyProperties(requestObject, bDTO);
//
////        // Create new
//        attTableLinesDTO createdRecord = tableService.create(bDTO);
//
////        //Prepare response
//        BeanUtils.copyProperties(createdRecord, returnValue);

        return returnValue;
    }


}