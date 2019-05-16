package modern.ui.entrypoint;

import modern.service.authService;
import modern.shared.dto.usersDTO;
import modern.ui.request.authRequest;
import modern.ui.response.authResponse;
import modern.utils.OptionsResource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/auth")
public class authEntryPoint extends OptionsResource {

    @Autowired
    authService authenticationService;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public authResponse userLogin(authRequest loginCredentials) {
        authResponse returnValue = new authResponse();


        usersDTO authenticatedUser = authenticationService.authenticate(loginCredentials.getEmail(), loginCredentials.getPassword());

        // Reset Access Token
        authenticationService.resetSecurityCridentials(loginCredentials.getPassword(),
                authenticatedUser);

        String accessToken = authenticationService.issueAccessToken(authenticatedUser);

        returnValue.setUserId(authenticatedUser.getUserId());
        returnValue.setToken(accessToken);

        return returnValue;
    }
}