package modern.ui.entrypoint;

import modern.annotations.CORS;
import modern.annotations.Secured;
import modern.service.usersService;
import modern.shared.dto.usersDTO;
import modern.ui.request.usersRequest;
import modern.ui.response.sys.DeleteResponseModel;
import modern.ui.response.sys.RequestOperation;
import modern.ui.response.sys.ResponseStatus;
import modern.ui.response.userResponse;
import modern.utils.OptionsResource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/users")
public class usersEntryPoint extends OptionsResource {

    @Autowired
    usersService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public userResponse createUser(usersRequest requestObject) {
        userResponse returnValue = new userResponse();

        // Prepare modern.shared.dto.usersDTO
        usersDTO userDto = new usersDTO();
        BeanUtils.copyProperties(requestObject, userDto);

        // Create new user
        usersDTO createdUserProfile = userService.create(userDto);

        //Prepare response
        BeanUtils.copyProperties(createdUserProfile, returnValue);

        return returnValue;
    }

    @CORS
    @Secured
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public userResponse getUserProfile(@PathParam("id") String id) {
        userResponse returnValue = null;

        usersDTO userProfile = userService.getbyId(id);

        returnValue = new userResponse();
        BeanUtils.copyProperties(userProfile, returnValue);

        return returnValue;
    }


    @Secured
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<userResponse> getUsers(@DefaultValue("0") @QueryParam("start") int start,
                                       @DefaultValue("50") @QueryParam("limit") int limit) {

        List<usersDTO> users = userService.getAll(start, limit);

        // Prepare return value
        List<userResponse> returnValue = new ArrayList<userResponse>();
        for (usersDTO userDto : users) {
            userResponse userModel = new userResponse();
            BeanUtils.copyProperties(userDto, userModel);
            returnValue.add(userModel);
        }

        return returnValue;
    }


    @Secured
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public userResponse updateUserDetails(@PathParam("id") String id,
                                          usersRequest userDetails) {

        usersDTO storedUserDetails = userService.getbyId(id);

        // Set only those fields you would like to be updated with this request
        if (userDetails.getFirstName() != null && !userDetails.getFirstName().isEmpty()) {
            storedUserDetails.setFirstName(userDetails.getFirstName());
        }
        storedUserDetails.setLastName(userDetails.getLastName());

        // Update User Details
        userService.update(storedUserDetails);

        // Prepare return value
        userResponse returnValue = new userResponse();
        BeanUtils.copyProperties(storedUserDetails, returnValue);


        return returnValue;
    }


    @Secured
    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public DeleteResponseModel deleteUserProfile(@PathParam("id") String id) {
        DeleteResponseModel returnValue = new DeleteResponseModel();
        returnValue.setRequestOperation(RequestOperation.DELETE);

        usersDTO storedUserDetails = userService.getbyId(id);

        userService.delete(storedUserDetails);

        returnValue.setResponseStatus(ResponseStatus.SUCCESS);

        return returnValue;
    }
}
