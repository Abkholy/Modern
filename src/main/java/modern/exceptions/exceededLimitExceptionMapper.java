package modern.exceptions;

import modern.ui.response.sys.ErrorMessage;
import modern.ui.response.sys.ErrorMessages;

import javax.ws.rs.core.Response;

public class exceededLimitExceptionMapper {
    public Response toResponse(Throwable exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(),
                ErrorMessages.Time_EXCEDDED.name(), "http://basar.tech");

        return Response.status(Response.Status.GATEWAY_TIMEOUT).
                entity(errorMessage).
                build();

    }
}
