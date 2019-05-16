package modern.utils;


import javax.annotation.security.PermitAll;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public class OptionsResource {
    @OPTIONS
    @PermitAll
    public Response options() {
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    // Match sub-resources
    @OPTIONS
    @Path("{path}")
    @PermitAll
    public Response optionsAll(@PathParam("path") String path) {
        System.out.println("path: " + path);
        return Response.status(Response.Status.NO_CONTENT).build();
    }


}
